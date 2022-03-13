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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public ResponseEntity<?> updateProduct(String token, ProductDto productDto) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        Product product = ProductMapper.INSTANCE.mapToProduct(productDto);

        productRepo.save(product);

        return ResponseEntity.ok(
                "Товар успешно обновлен!" +
                        ProductMapper
                                .INSTANCE
                                .mapToProductDto(product)
        );
    }

    @Override
    public ResponseEntity<?> getProductByBarcode(String token, String barcode) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return  responseEntity;
        }

        Product product = productRepo.findByBarcode(barcode);

        if (Objects.isNull(product)) {

            return new ResponseEntity<>(
                    new ResponseException("Товара с таким штрихкодом нет!", null)
                    ,HttpStatus.NOT_FOUND
            );
        }

        return ResponseEntity.ok(
                ProductMapper
                    .INSTANCE
                    .mapToProductDto(product)
        );
    }

    @Override
    public ResponseEntity<?> getAllProduct(String token) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        List<Product> productList = productRepo.findAll();

        return ResponseEntity.ok(productList
                .stream()
                .map(ProductMapper.INSTANCE::mapToProductDto)
                .collect(
                        Collectors.toList()
                ))       ;
    }



    @Override
    public ProductDto findProductByBarcodeForOperationDetails(String barcode) {

        return ProductMapper
                .INSTANCE
                .mapToProductDto(
                        productRepo
                                .findByBarcode(barcode));
    }
}
