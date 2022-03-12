package kg.itschool.sellserviceuser.service;

import kg.itschool.sellserviceuser.models.dtos.CodeDto;
import kg.itschool.sellserviceuser.models.dtos.UserDto;
import kg.itschool.sellserviceuser.models.entities.Code;

public interface CodeService {

    void saveCode(CodeDto codeDto);

    Code findLastCode(UserDto userDto);

    void sendCode(UserDto userDto);
}
