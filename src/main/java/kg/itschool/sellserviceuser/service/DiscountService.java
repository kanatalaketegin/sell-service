package kg.itschool.sellserviceuser.service;

import kg.itschool.sellserviceuser.models.dtos.DiscountDto;
import kg.itschool.sellserviceuser.models.dtos.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface DiscountService {
    ResponseEntity<?> saveDiscount(String token, DiscountDto discountDto);

    ResponseEntity<?> findDiscountByProduct(String token, ProductDto productDto);

    ResponseEntity<?> getAllDiscounts(String token);

    double getDiscountByProduct(ProductDto productDto);
}
