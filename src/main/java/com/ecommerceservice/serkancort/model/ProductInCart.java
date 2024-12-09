package com.ecommerceservice.serkancort.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product_in_cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCart extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @PrePersist
    @PreUpdate
    private void calculateTotalPrice() {
        this.totalPrice = product.getPrice().getPrice().multiply(BigDecimal.valueOf(totalAmount));
    }
}


