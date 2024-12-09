package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOPriceIU;
import com.ecommerceservice.serkancort.dto.outward.DTOPrice;
import com.ecommerceservice.serkancort.model.Price;
import org.mapstruct.*;

@Mapper(componentModel = "spring" ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PriceMapper {
    DTOPrice priceToDTO(Price price);
    Price priceToENTITY(DTOPriceIU dtoPrice);

}
