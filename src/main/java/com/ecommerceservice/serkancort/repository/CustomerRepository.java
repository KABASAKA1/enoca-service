package com.ecommerceservice.serkancort.repository;

import com.ecommerceservice.serkancort.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
