package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOProductStokIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProductStok;
import com.ecommerceservice.serkancort.model.ProductStok;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductStokMapper {
    @Mapping(source = "product.id",target = "productId")
    DTOProductStok productStokToDTO(ProductStok productStok);

    @Mapping(source = "product.price",target = "product.price.price")
    ProductStok productStokToENTITY(DTOProductStokIU dtoProductStok);

    @Mapping(source = "product.id",target = "productId")
    List<DTOProductStok> productStokToDTOList(List<ProductStok> productStoks);

    @Mapping(source = "product.price",target = "product.price.price")
    void updateProductStokFromDTO( DTOProductStokIU dtoProductStok ,@MappingTarget ProductStok productStok);

}
