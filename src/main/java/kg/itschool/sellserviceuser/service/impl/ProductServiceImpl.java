package kg.itschool.sellserviceuser.service.impl;

import kg.itschool.sellserviceuser.mappers.ProductMapper;
import kg.itschool.sellserviceuser.models.dtos.ProductDto;
import kg.itschool.sellserviceuser.models.entities.Product;
import kg.itschool.sellserviceuser.models.responce.ResponseException;
import kg.itschool.sellserviceuser.repository.ProductRepo;
import kg.itschool.sellserviceuser.service.ProductService;
import kg.itschool.sellserviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> saveProduct(String token, ProductDto productDto) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        Product product = ProductMapper
                .INSTANCE
                .mapToProduct(productDto);

        if (Objects.isNull(productRepo.findByNameOrBarcode(product.getName(), product.getBarcode()))) {

            productRepo.save(product);
            System.out.println("save product");
        } else {

            return new ResponseEntity<>(
                    new ResponseException("Такой товар уже существует!", null)
                    , HttpStatus.CONFLICT
            );
        }

        return ResponseEntity.ok("Товар успешно сохранен!" +
                ProductMapper
                        .INSTANCE
                        .mapToProductDto(product));
    }
}
