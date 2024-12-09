package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInCartIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProductInCart;
import com.ecommerceservice.serkancort.model.ProductInCart;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductInCartMapper {

    @Mapping(target = "cartId" , source = "cart.id")
    DTOProductInCart toDTO(ProductInCart productInCart);
    @Mappings({
            @Mapping(target = "cartId" , source = "cart.id"),
            @Mapping(target = "productId" ,source = "product.id"),
            @Mapping(target = "amount",source = "totalAmount")
    })
    DTOProductInCartIU toDTOIU(ProductInCart productInCart);
    @Mappings({
            @Mapping(source = "cartId" , target = "cart.id"),
            @Mapping(source = "productId" ,target = "product.id"),
            @Mapping(source = "amount",target = "totalAmount")
    })
    ProductInCart toENTITY(DTOProductInCartIU dtoProductInCart);
    @Mappings({
            @Mapping(source = "cartId" , target = "cart.id"),
            @Mapping(source = "productId" ,target = "product.id"),
            @Mapping(source = "amount",target = "totalAmount")
    })
    void updateEntityFromDTO(DTOProductInCartIU dtoProductInCart, @MappingTarget ProductInCart productInCart);

}
