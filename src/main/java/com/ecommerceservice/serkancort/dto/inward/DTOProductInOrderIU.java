package com.ecommerceservice.serkancort.dto.inward;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOProductInOrderIU {
    private Long id;

    private Long orderId;

    private Long productId;

    private Integer totalAmount;

}
