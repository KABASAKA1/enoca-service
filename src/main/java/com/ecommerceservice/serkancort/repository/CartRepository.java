package com.ecommerceservice.serkancort.repository;

import com.ecommerceservice.serkancort.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByCustomerId(Long customerId);

    @Query("Select c  From Cart c Join c.products d join d.product product where product.name Like %:keyword% ")
    Optional<List<Cart>> findAllCartByProductNameContainingKeyword(@Param("keyword") String keyword);
}
