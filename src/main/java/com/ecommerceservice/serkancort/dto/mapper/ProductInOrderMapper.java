package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInOrderIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProductInOrder;
import com.ecommerceservice.serkancort.model.ProductInOrder;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductInOrderMapper {


    DTOProductInOrder toDTO(ProductInOrder productInOrder);
    @Mappings({
                    @Mapping(source = "orderId", target = "order.id"),
                    @Mapping(source = "productId" ,target = "product.id")

    })
    ProductInOrder toENTITY(DTOProductInOrderIU dtoProductInOrder);
    @Mappings({
            @Mapping(source = "orderId", target = "order.id"),
            @Mapping(source = "productId" ,target = "product.id")

    })
    void updateEntityFromDTO(DTOProductInOrderIU dtoProductInOrder, @MappingTarget ProductInOrder productInOrder);

}
