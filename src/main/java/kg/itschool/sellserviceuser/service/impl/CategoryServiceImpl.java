package kg.itschool.sellserviceuser.service.impl;

import kg.itschool.sellserviceuser.mappers.CategoryMapper;
import kg.itschool.sellserviceuser.models.dtos.CategoryDto;
import kg.itschool.sellserviceuser.models.entities.Category;
import kg.itschool.sellserviceuser.models.responce.ResponseException;
import kg.itschool.sellserviceuser.repository.CategoryRepo;
import kg.itschool.sellserviceuser.service.CategoryService;
import kg.itschool.sellserviceuser.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> saveCategory(String token, CategoryDto categoryDto) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        Category category = CategoryMapper.INSTANCE.mapToCategory(categoryDto);

        if (Objects.isNull(categoryRepo.findByName(category.getName()))) {

            categoryRepo.save(category);
        } else {

            return new ResponseEntity<>(
                    new ResponseException("Такая категория уже существует!", null)
                    ,HttpStatus.CONFLICT
            );
        }

        return ResponseEntity.ok(CategoryMapper.INSTANCE.mapToCategoryDto(category) + "Категория успешно сохранена!");
    }

    @Override
    public ResponseEntity<?> getByName(String token, String name) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        Category category = categoryRepo.findByName(name);

        if (Objects.isNull(category)) {

            return new ResponseEntity<>(
                    new ResponseException(
                            "Такая категория не найдена!", null)
                    ,HttpStatus.NOT_FOUND
            );
        }

        return ResponseEntity.ok(
                CategoryMapper
                        .INSTANCE
                        .mapToCategoryDto(category)
        );
    }

    @SneakyThrows
    @Override
    public ResponseEntity<?> getAllCategories(String token) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {

            return responseEntity;
        }

        List<Category> categoryList = categoryRepo.findAll();

        return ResponseEntity.ok(categoryList
                .stream()
                .map(CategoryMapper
                        .INSTANCE::mapToCategoryDto)
                .collect(
                        Collectors.toList()
                ));
    }
}
