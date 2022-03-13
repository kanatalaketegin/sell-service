package kg.itschool.sellserviceuser.models.entities;

import kg.itschool.sellserviceuser.models.entities.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "barcode", unique = true)
    String barcode;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    boolean active;

}
