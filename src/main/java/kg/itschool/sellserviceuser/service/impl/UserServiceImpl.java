package kg.itschool.sellserviceuser.service.impl;

import kg.itschool.sellserviceuser.mappers.UserMapper;
import kg.itschool.sellserviceuser.models.dtos.UserDto;
import kg.itschool.sellserviceuser.models.entities.User;
import kg.itschool.sellserviceuser.models.responce.ResponseException;
import kg.itschool.sellserviceuser.repository.UserRepo;
import kg.itschool.sellserviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public ResponseEntity<?> saveUser(UserDto userDto) {

        User user = UserMapper.INSTANCE.mapToUser(userDto);

        if (Objects.isNull(userRepo.findByLogin(user.getLogin()))) {

            userRepo.save(user);
        } else {

            return new ResponseEntity<>(
                    new ResponseException("Пользователь уже существует", null), HttpStatus.CONFLICT
            );
        }
        return ResponseEntity.ok(UserMapper.INSTANCE.mapToUserDto(user)+ "Пользователь успешно сохранено!");
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepo.findByLogin(login);
    }
}
