package com.ecommerceservice.serkancort.repository;

import com.ecommerceservice.serkancort.model.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, Long> {
    Optional<ProductInCart> findByCartIdAndProductId(Long cartId , Long productId);
    Optional<ProductInCart> findProductInCartByCartId(Long cartId);
}
