package com.ecommerceservice.serkancort.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {
    @Column(name = "city" ,nullable = true)
    private String city;

    @Column(name = "district" ,nullable = true)
    private String district;

    @Column(name = "neighborhood" ,nullable = true)
    private String neighborhood;

    @Column(name = "street" ,nullable = true)
    private String street;
}
