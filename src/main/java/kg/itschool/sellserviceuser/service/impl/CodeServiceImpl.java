package kg.itschool.sellserviceuser.service.impl;

import kg.itschool.sellserviceuser.mappers.CodeMapper;
import kg.itschool.sellserviceuser.mappers.UserMapper;
import kg.itschool.sellserviceuser.models.dtos.CodeDto;
import kg.itschool.sellserviceuser.models.dtos.UserDto;
import kg.itschool.sellserviceuser.models.entities.Code;
import kg.itschool.sellserviceuser.models.enums.CodeStatus;
import kg.itschool.sellserviceuser.repository.CodeRepo;
import kg.itschool.sellserviceuser.service.CodeService;
import kg.itschool.sellserviceuser.service.SendSimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;


@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRepo codeRepo;

    @Autowired
    private SendSimpleMessage sendMessage;

    @Override
    public void saveCode(CodeDto codeDto) {
        codeRepo.save(CodeMapper.INSTANCE.mapToCode(codeDto));
    }

    @Override
    public Code findLastCode(UserDto userDto) {
        return codeRepo.findByUserAndCodeStatus(UserMapper.INSTANCE.mapToUser(userDto), CodeStatus.NEW);
    }

    @Override
    public void sendCode(UserDto userDto) {

        Code lastCode =
                codeRepo.findByUserAndCodeStatus(UserMapper.INSTANCE.mapToUser(userDto), CodeStatus.NEW);

        if (Objects.nonNull(lastCode)) {

            lastCode.setCodeStatus(CodeStatus.CANCELLED);

            codeRepo.save(lastCode);
        }

        int code = (int) ((Math.random() * 9000) + 1000);

        String hashedCode =
                BCrypt
                        .hashpw(
                                Integer
                                        .toString(code)
                                , BCrypt.gensalt());

        Calendar endOfCodeAction = Calendar.getInstance();
        endOfCodeAction.add(Calendar.MINUTE, 3);

        Code saveCode = new Code();
        saveCode.setCode(hashedCode);
        saveCode.setEndDate(endOfCodeAction.getTime());
        saveCode.setCodeStatus(CodeStatus.NEW);
        saveCode.setUser(UserMapper.INSTANCE.mapToUser(userDto));
        codeRepo.save(saveCode);

        sendMessage
                .sendSimpleMessage(
                        userDto.getEmail()
                        , Integer.toString(code));
    }
}
