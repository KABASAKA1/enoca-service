package com.ecommerceservice.serkancort.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Column()
    private String name;

    @Column( name = "phone_number", unique = true)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER , orphanRemoval = true)
    @JoinColumn(name = "address_id" , referencedColumnName = "id" )
    private Address address;

    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.EAGER )
    @JoinColumn(name = "cart_id" , referencedColumnName = "id")
    private Cart cart;

}


