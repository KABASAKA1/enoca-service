package com.ecommerceservice.serkancort.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Price price;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "stok_id")
    private ProductStok productStok;

    private Boolean isAvailable;

    @PreUpdate
    @PrePersist
    public void updateInStockStatus() {
        this.isAvailable = productStok != null &productStok.getStokAdet() != null & productStok.getStokAdet().compareTo(BigDecimal.ZERO) > 0;
    }

}


