package kg.itschool.sellserviceuser.models.entities;

import kg.itschool.sellserviceuser.models.entities.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operations")
@Entity
public class Operation extends BaseEntity {

    @CreationTimestamp
    @Column(name = "add_date")
    Date addDate;

    @Column(name = "total_amount")
    double totalAmount;

    double change;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    User user;

    double cash;
}
