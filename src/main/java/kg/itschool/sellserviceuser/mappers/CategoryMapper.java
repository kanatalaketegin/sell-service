package kg.itschool.sellserviceuser.mappers;

import kg.itschool.sellserviceuser.models.dtos.CategoryDto;
import kg.itschool.sellserviceuser.models.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category mapToCategory(CategoryDto categoryDto);

    CategoryDto mapToCategoryDto (Category category);
}
