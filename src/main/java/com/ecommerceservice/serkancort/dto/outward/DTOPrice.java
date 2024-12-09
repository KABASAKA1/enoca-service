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
public class DTOPrice {
    private Long id;

    private BigDecimal price;

}
