package kg.itschool.sellserviceuser.service.impl;

import kg.itschool.sellserviceuser.mappers.PriceMapper;
import kg.itschool.sellserviceuser.mappers.ProductMapper;
import kg.itschool.sellserviceuser.models.dtos.PriceDto;
import kg.itschool.sellserviceuser.models.dtos.ProductDto;
import kg.itschool.sellserviceuser.models.entities.Price;
import kg.itschool.sellserviceuser.models.responce.ResponseException;
import kg.itschool.sellserviceuser.repository.PriceRepo;
import kg.itschool.sellserviceuser.service.PriceService;
import kg.itschool.sellserviceuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepo priceRepo;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> savePrice(String token, PriceDto priceDto) {

        ResponseEntity<?> responseEntity =
                userService
                        .verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        List<Price> priceList =
                priceRepo
                        .findAllByProduct(
                                ProductMapper
                                        .INSTANCE
                                        .mapToProduct(
                                                priceDto
                                                        .getProduct()));

        if (Objects.nonNull(priceList) && !priceList.isEmpty()) {
            priceList
                    .stream()
                    .filter(
                            x -> x.getStartDate().before(priceDto.getStartDate())

                                    || x.getStartDate().after(priceDto.getStartDate())

                                    && x.getEndDate().before(priceDto.getEndDate())

                                    || x.getEndDate().after(priceDto.getEndDate()))
                    .forEach(x -> {

                        x.setEndDate(new Date());
                        priceRepo.save(x);
                    });
        }

        Price price =
                PriceMapper
                        .INSTANCE
                        .mapToPrice(priceDto);

        price = priceRepo.save(price);

        return ResponseEntity.ok(
                PriceMapper
                        .INSTANCE
                        .mapToPriceDto(price));
    }

    @Override
    public ResponseEntity<?> getPriceByProduct(String token, ProductDto productDto) {

        ResponseEntity<?> responseEntity =
                userService
                        .verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        Price price =
                priceRepo
                        .findActualPrice(
                                ProductMapper
                                        .INSTANCE
                                        .mapToProduct(productDto)
                        );

        if (Objects.isNull(price)) {

            return new ResponseEntity<>(
                    new ResponseException("Вы ввели некорректные данные!", null)
                    , HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(
                PriceMapper
                        .INSTANCE
                        .mapToPriceDto(price));
    }

    @Override
    public ResponseEntity<?> getAllPrices(String token) {

        ResponseEntity<?> responseEntity =
                userService
                        .verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        List<Price> priceList =
                priceRepo
                        .findAll();

        return ResponseEntity.ok(
                priceList
                        .stream()
                        .map(
                                PriceMapper
                                        .INSTANCE::mapToPriceDto)
                        .collect(
                                Collectors.toList()));
    }

    @Override
    public double findPriceByProductForOperationDetails(ProductDto productDto) {
        Price actualPrice =
                priceRepo
                        .findActualPrice(
                                ProductMapper
                                        .INSTANCE
                                        .mapToProduct(productDto));

        return actualPrice
                .getPrice();
    }
}