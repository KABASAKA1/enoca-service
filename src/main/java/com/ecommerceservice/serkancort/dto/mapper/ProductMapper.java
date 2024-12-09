package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOProductIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.ecommerceservice.serkancort.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    DTOProduct productToDTO(Product product);
    @Mapping(source = "stokAdet",target = "productStok.stokAdet")
    @Mapping(source = "price",target = "price.price")
    Product IUproductToENTITY(DTOProductIU dtoProduct);

    Product productToENTITY(DTOProduct dtoProduct);
    List<DTOProduct> productToDTOList(List<Product> products);
    @Mapping(source = "stokAdet",target = "productStok.stokAdet")
    @Mapping(source = "price",target = "price.price")
    void updateProductFromDTO( DTOProductIU dtoProduct ,@MappingTarget Product product);

}
