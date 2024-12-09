package com.ecommerceservice.serkancort.dto.inward;

import com.ecommerceservice.serkancort.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOOrderIU {
    private Long customerId;
    private Long addressId;
    private OrderStatus status;
}

