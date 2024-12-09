package com.ecommerceservice.serkancort.dto.inward;

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

    private String city;

    private String district;

    private String neighborhood;

    private String street;
}
