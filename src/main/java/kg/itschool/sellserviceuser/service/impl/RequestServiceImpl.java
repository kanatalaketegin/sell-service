package kg.itschool.sellserviceuser.service.impl;

import kg.itschool.sellserviceuser.mappers.CodeMapper;
import kg.itschool.sellserviceuser.models.dtos.CodeDto;
import kg.itschool.sellserviceuser.models.entities.Request;
import kg.itschool.sellserviceuser.repository.RequestRepo;
import kg.itschool.sellserviceuser.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepo requestRepo;

    @Override
    public void saveRequest(CodeDto checkUserCode, boolean value) {

        Request saveRequest = new Request();
        saveRequest
                .setCode(
                        CodeMapper
                                .INSTANCE
                                .mapToCode(checkUserCode));
        saveRequest.setSuccess(value);
        requestRepo.save(saveRequest);
    }

    @Override
    public int countFailedAttempts(CodeDto codeDto) {

        return requestRepo
                .countByCodeAndSuccess(
                        CodeMapper
                                .INSTANCE
                                .mapToCode(codeDto)
                        , false);
    }
}
