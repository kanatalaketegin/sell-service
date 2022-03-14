package kg.itschool.sellserviceuser.mappers;

import kg.itschool.sellserviceuser.models.dtos.OperationDto;
import kg.itschool.sellserviceuser.models.entities.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationMapper {

    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    Operation mapToOperation(OperationDto operationDto);

    OperationDto mapToOperationDto(Operation operation);
}
