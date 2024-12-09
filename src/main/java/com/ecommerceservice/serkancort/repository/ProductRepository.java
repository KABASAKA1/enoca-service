package com.ecommerceservice.serkancort.repository;

import com.ecommerceservice.serkancort.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
