package kg.itschool.sellserviceuser.service;

import kg.itschool.sellserviceuser.models.dtos.ProductDto;
import org.springframework.http.ResponseEntity;


public interface ProductService {
    ResponseEntity<?> saveProduct(String token, ProductDto productDto);

    ResponseEntity<?> updateProduct(String token, ProductDto productDto);

    ResponseEntity<?> getProductByBarcode(String token, String barcode);

    ResponseEntity<?> getAllProduct(String token);

    ProductDto findProductByBarcodeForOperationDetails(String barcode);
}
