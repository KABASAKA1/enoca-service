package com.ecommerceservice.serkancort.dto.inward;


import java.util.List;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Customer Id boş olamaz!")
    private Long customerId;

    private List<DTOProductInCartIU> products;

}

