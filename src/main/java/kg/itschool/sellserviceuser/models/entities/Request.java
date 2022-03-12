package kg.itschool.sellserviceuser.models.entities;

import kg.itschool.sellserviceuser.models.entities.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "requests")
public class Request extends BaseEntity {

    boolean success;

    @CreationTimestamp
    @Column(name = "add_date")
    Date addDate;

    @ManyToOne
    @JoinColumn(name = "code_id")
    Code code;
}
