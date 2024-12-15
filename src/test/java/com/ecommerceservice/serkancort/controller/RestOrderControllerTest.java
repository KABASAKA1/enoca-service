package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOOrderIU;
import com.ecommerceservice.serkancort.dto.outward.DTOOrder;
import com.ecommerceservice.serkancort.enums.OrderStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.ecommerceservice.serkancort.starter.EcommerceServiceApplication.class)
class RestOrderControllerTest {
    @Autowired
    private RestOrderController restOrderController;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getOrdersByCustomerName() throws JsonProcessingException {
        String customerName = "ahmet"; // Customer name düzenlenmeli

        ResponseEntity<List<DTOOrder>> ordersByCustomerName = restOrderController.getOrdersByCustomerName(customerName);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ordersByCustomerName);
        System.out.println(valueAsString);

    }

    @Test
    void getOrderByCustomerId() throws JsonProcessingException {
        Long orderID = 8L;  //Order ID düzenlenmeli

        ResponseEntity<List<DTOOrder>> ordersByCustomerId = restOrderController.getOrderByCustomerId(orderID);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ordersByCustomerId);
        System.out.println(valueAsString);
    }

    @Test
    void getOrderByOrderCode() throws JsonProcessingException {
        String orderCode = "DB8E893A4AF0";    // Order code düzenlenmeli

        ResponseEntity<DTOOrder> ordersByOrderCode = restOrderController.getOrderByOrderCode(orderCode);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ordersByOrderCode);
        System.out.println(valueAsString);
    }

    @Test
    void orderPlace() throws JsonProcessingException {
        DTOOrderIU order = new DTOOrderIU();

        order.setCustomerId(8L);    // Customer ID düzenlenmeli
        order.setAddressId(5L); // Address ID düzenlenmeli

        ResponseEntity<DTOOrder> orderResponseEntity = restOrderController.orderPlace(order.getCustomerId(), order);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderResponseEntity);
        System.out.println(valueAsString);
    }

    @Test
    void updateOrderAddress() throws JsonProcessingException {
        DTOOrderIU order = new DTOOrderIU();

        Long orderID = 17L;  // Order ID düzenlenmeli
        order.setCustomerId(8L);    // Customer ID validations için gerekli
        order.setAddressId(5L); // Address ID düzenlenmeli

        ResponseEntity<DTOOrder> orderResponseEntity = restOrderController.updateOrderAddress(orderID, order);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderResponseEntity);
        System.out.println(valueAsString);
    }

    @Test
    void updateOrderStatus() throws JsonProcessingException {
        DTOOrderIU order = new DTOOrderIU();

        Long orderID = 17L;  // Order ID düzenlenmeli
        order.setCustomerId(8L);    // Customer ID validations için gerekli
        order.setAddressId(5L); // Address ID vallidations için gerekli
        order.setStatus(OrderStatus.PREPARED);  // Order status düzenlenmeli

        ResponseEntity<DTOOrder> orderResponseEntity = restOrderController.updateOrderStatus(orderID , order);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderResponseEntity);
        System.out.println(valueAsString);
    }

    @Test
    void deleteOrderByOrderCode() {
        String orderCode = "Sb23kjnb12";    // Order code düzenlenmeli

        restOrderController.deleteOrderByOrderCode(orderCode);
    }
}