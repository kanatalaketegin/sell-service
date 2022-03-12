package kg.itschool.sellserviceuser.models.dtos;

import kg.itschool.sellserviceuser.models.enums.CodeStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeDto {

    Long id;
    String code;
    Date startDate;
    Date endDate;
    CodeStatus codeStatus;
    UserDto user;
}
