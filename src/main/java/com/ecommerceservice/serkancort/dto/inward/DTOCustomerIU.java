package com.ecommerceservice.serkancort.dto.inward;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOCustomerIU {
    private Long id;
    @NotEmpty(message = "İsim boş olamaz!")
    @Size(min = 2, max = 20, message = "İsim en az 2, en fazla 50 karakter olmalıdır!")
    private String name;
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Telefon numarası geçerli değil!")
    private String phoneNumber;
    @NotNull(message = "Address null olamaz!")
    @Valid
    private DTOAddressIU address;
}
