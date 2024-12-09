package com.ecommerceservice.serkancort.model;

import com.ecommerceservice.serkancort.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orderr")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false , unique = true , name = "order_code")
    private String orderCode;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "shipping_address")
    private AddressInOrder shippingAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order" , fetch = FetchType.EAGER ,cascade = CascadeType.ALL , orphanRemoval = true)
    private List<ProductInOrder> products = new ArrayList<>();
}


