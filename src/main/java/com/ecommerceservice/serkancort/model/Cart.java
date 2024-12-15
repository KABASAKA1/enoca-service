package com.ecommerceservice.serkancort.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER )
    @JoinColumn(name = "customer_id" , nullable = true)
    private Customer customer;

    @OneToMany(mappedBy = "cart" ,cascade = CascadeType.ALL , fetch = FetchType.EAGER , orphanRemoval = true )
    private List<ProductInCart> products = new ArrayList<>();

    @Column(name = "total_price" , nullable = true)
    private BigDecimal totalPrice;

}


