package com.ecommerceservice.serkancort.dto.outward;

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
public class DTOCustomer {
    private Long id;

    private String name;

    private String phoneNumber;

    private DTOAddress address;

    private DTOCart cart;

}
