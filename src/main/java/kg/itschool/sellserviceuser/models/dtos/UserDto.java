package kg.itschool.sellserviceuser.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    String name;
    String login;
    boolean active;
    String email;
    Date endOfBlockDate;

}
