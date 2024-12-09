package com.ecommerceservice.serkancort.dto.outward;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOProductInCart {
    private Long id;

    private long cartId;

    private DTOProduct product;

    private Integer totalAmount;

    private BigDecimal totalPrice;

}


