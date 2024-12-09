package com.ecommerceservice.serkancort.dto.outward;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOAddressInOrder {
    Long id;
    DTOAddress address;
}
