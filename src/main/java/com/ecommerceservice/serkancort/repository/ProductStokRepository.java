package com.ecommerceservice.serkancort.repository;

import com.ecommerceservice.serkancort.model.ProductStok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductStokRepository extends JpaRepository<ProductStok, Long> {
    Optional<ProductStok> findByProductId(Long productId);
}
