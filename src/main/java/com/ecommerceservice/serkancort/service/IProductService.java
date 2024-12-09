package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.ecommerceservice.serkancort.dto.inward.DTOProductIU;

import java.util.List;

public interface IProductService {
    public DTOProduct createPruduct(DTOProductIU product);
    public List<DTOProduct> getAllPruducts();
    public DTOProduct getProductById(Long id);
    public DTOProduct updateProductPrice(Long id, DTOProductIU request);
    public DTOProduct updateProductStock(Long id , DTOProductIU request);
    public void deleteProduct(Long id);
}
