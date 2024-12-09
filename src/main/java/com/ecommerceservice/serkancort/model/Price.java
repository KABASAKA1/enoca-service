package com.ecommerceservice.serkancort.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_stok_id" , referencedColumnName = "id")
    private ProductStok productStok;

    @Column
    private BigDecimal price;
}



