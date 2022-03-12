package kg.itschool.sellserviceuser.service.impl;

import io.jsonwebtoken.*;
import kg.itschool.sellserviceuser.mappers.CodeMapper;
import kg.itschool.sellserviceuser.mappers.UserMapper;
import kg.itschool.sellserviceuser.models.dtos.CodeDto;
import kg.itschool.sellserviceuser.models.dtos.UserDto;
import kg.itschool.sellserviceuser.models.entities.User;
import kg.itschool.sellserviceuser.models.enums.CodeStatus;
import kg.itschool.sellserviceuser.models.responce.ResponseException;
import kg.itschool.sellserviceuser.models.responce.SuccessLogin;
import kg.itschool.sellserviceuser.repository.UserRepo;
import kg.itschool.sellserviceuser.service.CodeService;
import kg.itschool.sellserviceuser.service.RequestService;
import kg.itschool.sellserviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

//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
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CodeService codeService;

    @Autowired
    private RequestService requestService;

    @Value("${jwtSecret}")
    private String secretKey;

    @Override
    public ResponseEntity<?> saveUser(UserDto userDto) {

        User user =
                UserMapper
                        .INSTANCE
                        .mapToUser(userDto);

        if (Objects.isNull(userRepo.findByLogin(user.getLogin()))) {
            userRepo
                    .save(user);
        } else {
            return new ResponseEntity<>(
                    new ResponseException("Пользователь уже существует", null)
                    , HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(
                UserMapper
                        .INSTANCE
                        .mapToUserDto(user));
    }

    @Override
    public boolean userLockOutChecking(User user) {

        if (Objects.nonNull(user.getEndOfBlockDate())) {

            if (new Date().before(user.getEndOfBlockDate())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    @Override
    public ResponseEntity<?> sendCode(String login) {

        User user = userRepo.findByLogin(login);

        if (Objects.isNull(user)) {
            return new ResponseEntity<>(
                    new ResponseException("Некорректный логин!", null)
                    , HttpStatus.NOT_FOUND);
        }

        boolean check = userLockOutChecking(user);

        if (check) {
            SimpleDateFormat formatToShowEndOfBlockDate =
                    new SimpleDateFormat("hh:mm a");

            return new ResponseEntity<>(" Превышено количество попыток входа, вы заблокированы. Повторите попытку в " +
                    formatToShowEndOfBlockDate
                            .format(
                                    user.getEndOfBlockDate()), HttpStatus.CONFLICT);
        }

        codeService.sendCode(
                UserMapper
                        .INSTANCE
                        .mapToUserDto(user));

        return ResponseEntity.ok(
                new ResponseException("Код подтверждения успешно отправлен!", null));
    }

    @Override
    public ResponseEntity<?> getToken(String login, String code) {

        User user = userRepo.findByLogin(login);

        if (Objects.isNull(user)) {

            return new ResponseEntity<>(
                    new ResponseException("Некорректный логин!", null)
                    , HttpStatus.NOT_FOUND);
        }

        boolean check = userLockOutChecking(user);

        if (check) {
            SimpleDateFormat formatToShowEndOfBlockDate =
                    new SimpleDateFormat("hh:mm a");

            return new ResponseEntity<>(" Превышено количество попыток входа, вы заблокированы. Повторите попытку в " +
                    formatToShowEndOfBlockDate
                            .format(
                                    user.getEndOfBlockDate()), HttpStatus.CONFLICT);
        }

        CodeDto checkUserCode =
                CodeMapper
                        .INSTANCE
                        .mapToCodeDto(
                                codeService
                                        .findLastCode(
                                                UserMapper
                                                        .INSTANCE
                                                        .mapToUserDto(user)));


        if (new Date().after(checkUserCode.getEndDate())) {
            return new ResponseEntity<>(
                    new ResponseException(
                            "Время действия кода подтверждения истек!"
                            , "Вам нужно получить код подтверждения повторно!")
                    , HttpStatus.CONFLICT
            );
        }

        if (!BCrypt.checkpw(code, checkUserCode.getCode())) {

            requestService.saveRequest(checkUserCode, false);

            if (requestService.countFailedAttempts(checkUserCode) >= 3) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, 1);

                user.setEndOfBlockDate(calendar.getTime());
                userRepo.save(user);

                checkUserCode.setCodeStatus(CodeStatus.FAILED);
                codeService.saveCode(checkUserCode);
            }

            return new ResponseEntity<>(
                    new ResponseException("Авторизация не пройдена!", "Вы ввели некорректный код подтверждения!")
                    , HttpStatus.NOT_FOUND);

        }

        requestService.saveRequest(checkUserCode, true);

        Calendar tokensTimeLive =
                Calendar.getInstance();
        tokensTimeLive
                .add(Calendar.HOUR, 3);

        String token =
                Jwts.builder()
                        .claim("login", login)
                        .setExpiration(
                                tokensTimeLive
                                        .getTime())
                        .signWith(
                                SignatureAlgorithm.HS256
                                , secretKey)
                        .compact();

        checkUserCode.setCodeStatus(CodeStatus.APPROVED);
        codeService.saveCode(checkUserCode);

        SuccessLogin successLogin = new SuccessLogin("Вы успешно ввели пароль!", token);

        return ResponseEntity.ok(successLogin);
    }

    @Override
    public ResponseEntity<?> verifyLogin(String token) {
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return ResponseEntity.ok(jwt.getBody().get("login"));
        } catch (ExpiredJwtException jwtException) {

            return new ResponseEntity<>("Время действия токена истек", HttpStatus.CONFLICT);
        } catch (UnsupportedJwtException jwtException) {

            return new ResponseEntity<>("Неподерживаемый токен", HttpStatus.CONFLICT);
        } catch (MalformedJwtException jwtException) {

            return new ResponseEntity<>("Некорректный токен", HttpStatus.CONFLICT);
        } catch (SignatureException signatureException) {

            return new ResponseEntity<>("Некорректная подпись в токене!", HttpStatus.CONFLICT);
        } catch (Exception exception) {

            return new ResponseEntity<>("unauthorized", HttpStatus.CONFLICT);
        }
    }
}
