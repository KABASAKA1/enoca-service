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
@Table(name = "product_stok_information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStok extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER ,orphanRemoval = true)
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    private Product product;

    @Column(name = "stok_adet")
    private BigDecimal stokAdet;

    @OneToMany(mappedBy = "productStok" , cascade = CascadeType.ALL, fetch = FetchType.EAGER ,orphanRemoval = true)
    private List<Price> priceHistory = new ArrayList<>();

    @PreUpdate
    public void updateInStockStatus() {
        product.setIsAvailable(stokAdet != null && stokAdet.compareTo(BigDecimal.ZERO) > 0);
    }

}

