package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInCartIU;
import com.ecommerceservice.serkancort.dto.outward.DTOCart;


public interface ICartService {
    public DTOCart getCartById(Long id);
    public DTOCart getCartByCustomerId(Long customerId);
    public DTOCart addProductToCart(Long cartId , DTOProductInCartIU request);
    public DTOCart removeProductFromCart(Long cartId , DTOProductInCartIU request);
    public DTOCart emptyCart(Long cartId);
}
