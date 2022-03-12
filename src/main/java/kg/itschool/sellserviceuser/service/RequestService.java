package kg.itschool.sellserviceuser.service;

import kg.itschool.sellserviceuser.models.dtos.CodeDto;

public interface RequestService {

    void saveRequest(CodeDto codeDto, boolean value);

    int countFailedAttempts(CodeDto codeDto);
}
