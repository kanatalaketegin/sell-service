package kg.itschool.sellserviceuser.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto {

    Long id;
    double price;
    Date startDate;
    Date endDate;
    ProductDto product;
}
