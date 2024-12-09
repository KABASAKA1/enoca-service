package com.ecommerceservice.serkancort.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product_in_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInOrder extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne (fetch = FetchType.EAGER )
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
}
