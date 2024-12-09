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
public class DTOProductInOrder {
    private Long id;

    private DTOProduct product;

    private Integer totalAmount;

    private BigDecimal totalPrice;

}


