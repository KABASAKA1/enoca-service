package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.inward.DTOOrderIU;
import com.ecommerceservice.serkancort.dto.outward.DTOOrder;

import java.util.List;

public interface IOrderService {
    public DTOOrder orderPlace( Long customerId ,DTOOrderIU dtoOrderIU);
    public DTOOrder updateOrderAddress(Long orderId , DTOOrderIU dtoOrderIU);
    public DTOOrder updateOrderStatus(Long orderId , DTOOrderIU dtoOrderIU);

    public List<DTOOrder> getOrderByCustomerId(Long customerId);
    public DTOOrder getOrderByOrderCode(String orderCode);
    public void deleteOrderByOrderCode(String orderCode);


}
