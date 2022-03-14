package kg.itschool.sellserviceuser.service.impl;

import kg.itschool.sellserviceuser.mappers.OperationDetailMapper;
import kg.itschool.sellserviceuser.models.dtos.OperationDetailDto;
import kg.itschool.sellserviceuser.repository.OperationDetailRepo;
import kg.itschool.sellserviceuser.service.OperationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationDetailServiceImpl implements OperationDetailService {

    @Autowired
    private OperationDetailRepo operationDetailRepo;

    @Override
    public void saveOperationDetails(List<OperationDetailDto> operationDetailDtoList) {

        for (OperationDetailDto element: operationDetailDtoList) {

            operationDetailRepo
                    .save(
                            OperationDetailMapper
                                    .INSTANCE
                                    .mapToOperationDetail(element)
                    );
        }
    }
}
