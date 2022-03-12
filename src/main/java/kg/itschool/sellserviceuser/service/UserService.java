package kg.itschool.sellserviceuser.service;

import kg.itschool.sellserviceuser.models.dtos.UserDto;
import kg.itschool.sellserviceuser.models.entities.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> saveUser(UserDto userDto);

    User findUserByLogin(String login);

    boolean userLockOutChecking(User user);

    ResponseEntity<?> sendCode(String login);

    ResponseEntity<?> getToken(String login, String code);

    ResponseEntity<?> verifyLogin(String token);
}
