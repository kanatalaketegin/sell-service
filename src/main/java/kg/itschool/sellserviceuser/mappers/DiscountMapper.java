package kg.itschool.sellserviceuser.mappers;

import kg.itschool.sellserviceuser.models.dtos.DiscountDto;
import kg.itschool.sellserviceuser.models.entities.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    Discount mapToDiscount(DiscountDto discountDto);

    DiscountDto mapToDiscountDto(Discount discount);
}
