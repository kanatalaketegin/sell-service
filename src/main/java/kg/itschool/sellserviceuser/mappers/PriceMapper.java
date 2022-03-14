package kg.itschool.sellserviceuser.mappers;

import kg.itschool.sellserviceuser.models.dtos.PriceDto;
import kg.itschool.sellserviceuser.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    Price mapToPrice (PriceDto priceDto);

    PriceDto mapToPriceDto (Price price);
}
