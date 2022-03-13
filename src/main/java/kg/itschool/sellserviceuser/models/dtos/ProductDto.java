package kg.itschool.sellserviceuser.models.dtos;

import kg.itschool.sellserviceuser.models.entities.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    Long id;
    String name;
    String barcode;
    Category category;
    boolean active;

}
