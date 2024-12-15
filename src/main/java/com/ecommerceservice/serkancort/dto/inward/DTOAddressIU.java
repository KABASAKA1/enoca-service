package com.ecommerceservice.serkancort.dto.inward;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOAddressIU {
    private Long id;
    @NotBlank(message = "Şehir boş olamaz!")
    @Size(min = 2, max = 20, message = "Şehir en az 2, en fazla 50 karakter olmalıdır!")
    private String city;

    @NotBlank(message = "İlçe boş olamaz!")
    @Size(min = 2, max = 20, message = "İlçe en az 2, en fazla 50 karakter olmalıdır!")
    private String district;

    @NotBlank(message = "Mahalle boş olamaz!")
    @Size(min = 2, max = 20, message = "Mahalle en az 2, en fazla 50 karakter olmalıdır!")
    private String neighborhood;

    @NotBlank(message = "Sokak boş olamaz!")
    @Size(min = 2, max = 20, message = "Sokak en az 2, en fazla 50 karakter olmalıdır!")
    private String street;
}
