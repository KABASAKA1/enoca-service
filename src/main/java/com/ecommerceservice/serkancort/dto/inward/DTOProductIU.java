package com.ecommerceservice.serkancort.dto.inward;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOProductIU {

    private Long id;

    @NotBlank(message = "Product name boş olamaz!")
    @Size(min = 2, max = 20, message = "Product name en az 2, en fazla 50 karakter olmalıdır!")
    private String name;
    @NotNull(message = "Product price boş olamaz!")
    private BigDecimal price;
    @NotNull(message = "Product stok adet boş olamaz!")
    private BigDecimal stokAdet;

}

