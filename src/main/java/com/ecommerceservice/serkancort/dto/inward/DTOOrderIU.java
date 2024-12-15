package com.ecommerceservice.serkancort.dto.inward;

import com.ecommerceservice.serkancort.enums.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOOrderIU {
    @NotNull(message = "Customer ID boş olamaz!")
    private Long customerId;
    @NotNull(message = "Address ID boş olamaz!")
    private Long addressId;
    private OrderStatus status;
}

