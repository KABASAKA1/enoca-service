package com.ecommerceservice.serkancort.dto.inward;

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
public class DTOProductStokIU {
    private Long id;

    private BigDecimal stokAdet;
    private DTOProductIU product;

}


