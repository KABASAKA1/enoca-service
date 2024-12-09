package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOOrderIU;
import com.ecommerceservice.serkancort.dto.outward.DTOOrder;
import com.ecommerceservice.serkancort.model.Order;
import com.ecommerceservice.serkancort.model.ProductInCart;
import com.ecommerceservice.serkancort.model.ProductInOrder;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    @Mappings({
            @Mapping(target = "id" , ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    ProductInOrder itemsToOrderPlace (ProductInCart dtoProductInCart);
    @Mappings({
            @Mapping(target = "id" , ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    List<ProductInOrder> listToOrderPlace(List<ProductInCart> productsInCart);


    DTOOrder orderToDTO(Order order);

    Order orderToENTITY(DTOOrderIU dtoOrder);
    List<DTOOrder> orderToDTOList(List<Order> orders);
    void updateOrderFromDTO( DTOOrderIU dtoOrder ,@MappingTarget Order order);
}

