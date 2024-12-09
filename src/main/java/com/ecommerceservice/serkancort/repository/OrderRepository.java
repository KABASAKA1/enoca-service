package com.ecommerceservice.serkancort.repository;

import com.ecommerceservice.serkancort.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // customerId ile siparişleri bulma
    List<Order> findByCustomerId(Long customerId);


    // OrderCode ile sipariş bulma
    Optional<Order> findByOrderCode(String orderCode);

    @Query("select o ,c from Order o join o.customer c where c.name = :customerName ")
    List<Order> findOrderByCustomerName(@Param("customerName") String customerName);
}
