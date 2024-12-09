package com.ecommerceservice.serkancort.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address_in_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressInOrder extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "address_id")
    Address address;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
}
