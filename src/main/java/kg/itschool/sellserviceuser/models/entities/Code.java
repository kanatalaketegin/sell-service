package kg.itschool.sellserviceuser.models.entities;

import kg.itschool.sellserviceuser.models.entities.base.BaseEntity;
import kg.itschool.sellserviceuser.models.enums.CodeStatus;
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
@Table(name = "codes")
public class Code extends BaseEntity {

    @Column(name = "code")
    String code;

    @CreationTimestamp
    @Column(name = "start_date")
    Date startDate;

    @Column(name = "end_date")
    Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "code_status")
    CodeStatus codeStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
