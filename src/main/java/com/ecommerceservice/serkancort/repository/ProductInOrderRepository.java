package com.ecommerceservice.serkancort.repository;

import com.ecommerceservice.serkancort.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}
