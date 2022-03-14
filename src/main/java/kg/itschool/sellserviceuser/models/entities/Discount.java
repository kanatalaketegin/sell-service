package kg.itschool.sellserviceuser.models.entities;

import kg.itschool.sellserviceuser.models.entities.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discounts")
@Entity
public class Discount extends BaseEntity {

    @Column(name = "discount")
    double discount;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "end_date")
    Date endDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
