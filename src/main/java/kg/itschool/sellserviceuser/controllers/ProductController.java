package kg.itschool.sellserviceuser.controllers;

import kg.itschool.sellserviceuser.models.dtos.ProductDto;
import kg.itschool.sellserviceuser.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/saveProduct")
    public ResponseEntity<?> saveProduct(@RequestHeader String token, @RequestBody ProductDto productDto) {
        return productService.saveProduct(token, productDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestHeader String token, @RequestBody ProductDto productDto) {
        return productService.updateProduct(token, productDto);
    }

    @GetMapping("/getByBarcode")
    public ResponseEntity<?> getProductByBarcode (@RequestHeader String token, @RequestParam String barcode) {
        return  productService.getProductByBarcode(token, barcode);
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct (@RequestHeader String token) {
        return productService.getAllProduct(token);
    }
}
