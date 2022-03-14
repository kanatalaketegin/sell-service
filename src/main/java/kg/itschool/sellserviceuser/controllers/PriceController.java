package kg.itschool.sellserviceuser.controllers;

import kg.itschool.sellserviceuser.models.dtos.PriceDto;
import kg.itschool.sellserviceuser.models.dtos.ProductDto;
import kg.itschool.sellserviceuser.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/save")
    public ResponseEntity<?> savePrice(@RequestHeader String token, @RequestBody PriceDto priceDto) {
        return priceService.savePrice(token, priceDto);
    }

    @PostMapping("/getByProduct")
    public ResponseEntity<?> getByProduct(@RequestHeader String token, @RequestBody ProductDto productDto) {
        return priceService.getPriceByProduct(token, productDto);
    }

    @GetMapping("/getAllPrices")
    public ResponseEntity<?> getAllPrices(@RequestHeader String token) {
        return priceService.getAllPrices(token);
    }
}