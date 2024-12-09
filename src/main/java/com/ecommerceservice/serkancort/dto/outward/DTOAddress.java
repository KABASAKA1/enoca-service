package com.ecommerceservice.serkancort.dto.outward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOAddress {

    private Long id;

    private String city;

    private String district;

    private String neighborhood;

    private String street;
}
