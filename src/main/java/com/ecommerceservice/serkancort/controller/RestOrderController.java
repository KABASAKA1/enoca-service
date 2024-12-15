package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOCustomerIU;
import com.ecommerceservice.serkancort.dto.inward.DTOOrderIU;
import com.ecommerceservice.serkancort.dto.outward.DTOOrder;
import com.ecommerceservice.serkancort.service.imp.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/order")
public class RestOrderController {
    private final OrderService orderService;

    @GetMapping("/{customerName}/byCustomerName")
    public ResponseEntity<List<DTOOrder>> getOrdersByCustomerName(@PathVariable(name = "customerName") String customerName) {
        List<DTOOrder> orders = orderService.getOrderByCustomerName(customerName);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{customerId}/byCustomer")
    public ResponseEntity<List<DTOOrder>> getOrderByCustomerId(@PathVariable(name = "customerId") @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long customerId) {
        List<DTOOrder> orders = orderService.getOrderByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderCode}/byOrderCode")
    public ResponseEntity<DTOOrder> getOrderByOrderCode( @PathVariable(name = "orderCode") String orderCode) {
        DTOOrder order = orderService.getOrderByOrderCode(orderCode);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/customers/{customerId}/order")
    public ResponseEntity<DTOOrder> orderPlace( @PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long customerId, @Valid @RequestBody DTOOrderIU order) {
        DTOOrder dtoOrder = orderService.orderPlace(customerId , order);
        return ResponseEntity.ok(dtoOrder);
    }

    @PutMapping("/{orderId}/address")
    public ResponseEntity<DTOOrder> updateOrderAddress(@PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long orderId , @Valid @RequestBody DTOOrderIU request){
        DTOOrder dtoOrder = orderService.updateOrderAddress(orderId , request);
        return ResponseEntity.ok(dtoOrder);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<DTOOrder> updateOrderStatus(@PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long orderId , @Valid @RequestBody DTOOrderIU request){
        DTOOrder dtoOrder = orderService.updateOrderStatus(orderId , request);
        return ResponseEntity.ok(dtoOrder);
    }

    @DeleteMapping("/{orderCode}/byOrderCode")
    public ResponseEntity<Void> deleteOrderByOrderCode(@PathVariable(name = "orderCode") String orderCode) {
        orderService.deleteOrderByOrderCode(orderCode);
        return ResponseEntity.noContent().build();
    }
}
