package com.ecommerceservice.serkancort.dto.inward;


import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOCartIU {
    private Long id;

    private Long customerId;

    private List<DTOProductInCartIU> products;

}

