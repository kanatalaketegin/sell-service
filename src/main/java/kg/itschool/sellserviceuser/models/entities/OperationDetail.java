package kg.itschool.sellserviceuser.models.entities;

import com.fasterxml.jackson.databind.ser.Serializers;
import kg.itschool.sellserviceuser.models.entities.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation_details")
@Entity
public class OperationDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "operation_id")
    Operation operation;

    int quantity;
    double amount;
}
