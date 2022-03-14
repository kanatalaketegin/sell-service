package kg.itschool.sellserviceuser.service;

import kg.itschool.sellserviceuser.models.dtos.OperationDetailDto;

import java.util.List;

public interface OperationDetailService {

    void saveOperationDetails(List<OperationDetailDto> operationDetailDtoList);
}
