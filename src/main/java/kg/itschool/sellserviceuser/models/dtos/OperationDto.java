package kg.itschool.sellserviceuser.models.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDto {

    Long id;
    Date addDate;
    double totalAmount;
    double change;
    UserDto user;
    double cash;
}
