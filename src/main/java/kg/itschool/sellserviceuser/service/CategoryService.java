package kg.itschool.sellserviceuser.service;

import kg.itschool.sellserviceuser.models.dtos.CategoryDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<?> saveCategory(String token, CategoryDto categoryDto);

    ResponseEntity<?> getByName(String token, String name);

    ResponseEntity<?> getAllCategories(String token);
}
