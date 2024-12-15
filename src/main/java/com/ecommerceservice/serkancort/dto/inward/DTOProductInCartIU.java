package com.ecommerceservice.serkancort.dto.inward;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOProductInCartIU {

    @NotNull(message = "Cart ID boş olamaz!")
    private Long cartId;
    @NotNull(message = "Product ID boş olamaz!")
    private Long productId;
    @NotNull(message = "Product amount boş olamaz!")
    private Integer amount;

}


