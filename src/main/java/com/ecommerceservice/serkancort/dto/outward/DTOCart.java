package com.ecommerceservice.serkancort.dto.outward;


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
public class DTOCart {
    private Long id;

    private Long customerId;

    private List<DTOProductInCart> products = new ArrayList<>();

    private BigDecimal totalPrice;

}



