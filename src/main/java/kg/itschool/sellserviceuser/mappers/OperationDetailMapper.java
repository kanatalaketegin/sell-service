package kg.itschool.sellserviceuser.mappers;

import kg.itschool.sellserviceuser.models.dtos.OperationDetailDto;
import kg.itschool.sellserviceuser.models.entities.OperationDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationDetailMapper {

    OperationDetailMapper INSTANCE = Mappers.getMapper(OperationDetailMapper.class);

    OperationDetail mapToOperationDetail(OperationDetailDto operationDetailDto);

    OperationDetailDto mapToOperationDetailDto(OperationDetail operationDetail);
}
