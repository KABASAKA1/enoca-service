package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInCartIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProductInCart;

public interface IProductInCartService {
    DTOProductInCart createProductInCart(DTOProductInCartIU request);
    DTOProductInCart getProductInCartById(Long id);
    DTOProductInCart updateProductInCart(Long id , DTOProductInCartIU request);
    DTOProductInCart getProductInCartByIDs(Long cartId , Long productId);
    void deleteProductInCartById(Long id);
}
