package kg.itschool.sellserviceuser.models.entities;

import kg.itschool.sellserviceuser.models.entities.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "login", unique = true, nullable = false)
    String login;

    @Column(name = "active")
    boolean active;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    @Column(name = "blockDate")
    Date endOfBlockDate;
}
