package com.ecommerceservice.serkancort.dto.outward;

import com.ecommerceservice.serkancort.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOOrder {
    private Long id;

    private BigDecimal totalPrice;

    private OrderStatus status;

    private String orderCode;

    private DTOAddressInOrder shippingAddress;

    private DTOCustomer customer;

    private List<DTOProductInOrder> products = new ArrayList<>();
}




