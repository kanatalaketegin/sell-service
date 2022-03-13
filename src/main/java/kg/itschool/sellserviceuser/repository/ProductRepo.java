package kg.itschool.sellserviceuser.repository;

import kg.itschool.sellserviceuser.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findByNameOrBarcode(String name, String barcode);

    Product findByBarcode(String barcode);
}
