package kg.itschool.sellserviceuser.models.entities;

import kg.itschool.sellserviceuser.models.entities.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "prices")
public class Price extends BaseEntity {

    @Column(name = "price")
    double price;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "end_date")
    Date endDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
