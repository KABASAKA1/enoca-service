package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInOrderIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProductInOrder;

public interface IProductInOrderService {
    DTOProductInOrder createProductInOrder(DTOProductInOrderIU request);
    DTOProductInOrder getProductInOrderById(Long id);
    DTOProductInOrder updateProductInOrder(Long id , DTOProductInOrderIU request);
}
