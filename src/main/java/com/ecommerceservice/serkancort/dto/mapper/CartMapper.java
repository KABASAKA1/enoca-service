package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOCartIU;
import com.ecommerceservice.serkancort.dto.outward.DTOCart;
import com.ecommerceservice.serkancort.model.Cart;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring" ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartMapper {

    @Mapping(source = "customer.id" ,target = "customerId")
    DTOCart cartToDTO(Cart cart);

    @Mapping(source = "customerId" ,target = "customer.id")
    Cart IUcartToENTITY(DTOCartIU dtoCart);

    @Mapping(source = "customerId",target = "customer.id")
    Cart cartToEntity(DTOCart dtoCart);

    @Mapping(source = "customerId" ,target = "customer.id")
    void updateCartFromDTO( DTOCartIU dtoCartIU ,@MappingTarget Cart cart);

    @Mapping(source = "customer.id" ,target = "customerId")
    List<DTOCart> customerToDTOList(List<Cart> carts);
}
