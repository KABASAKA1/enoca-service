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
public class DTOPriceIU {
    private Long id;
    @NotNull(message = "Product stok ID boş olamaz!")
    private Long productStokId;
    @NotNull(message = "Price boş olamaz!")
    private BigDecimal price;

}
