package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInCartIU;
import com.ecommerceservice.serkancort.dto.outward.DTOCart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.ecommerceservice.serkancort.starter.EcommerceServiceApplication.class)
class RestCartControllerTest {
    @Autowired
    private RestCartController restCartController;
    @Autowired
    private ObjectMapper objectMapper ;

    @Test
    void getCartById() throws JsonProcessingException {
        Long cartID = 4L;   // Cart ID düzenlenmeli

        ResponseEntity<DTOCart> cartById = restCartController.getCartById(cartID);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartById);
        System.out.println(valueAsString);
    }

    @Test
    void getCartByCustomerId() throws JsonProcessingException {
        Long customerID = 4L;   // Customer ID düzenlenmeli

        ResponseEntity<DTOCart> cartById = restCartController.getCartByCustomerId(customerID);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartById);
        System.out.println(valueAsString);
    }

    @Test
    void getCartByProductNameKeyword() throws JsonProcessingException {
        String keyword = "el"; // Keyword düzenlenmeli

        ResponseEntity<List<DTOCart>> cartByProductNameKeyword = restCartController.getCartByProductNameKeyword(keyword);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartByProductNameKeyword);
        System.out.println(valueAsString);
    }

    @Test
    void addProductToCart() throws JsonProcessingException {
        DTOProductInCartIU dtoProductInCartIU = new DTOProductInCartIU();

        dtoProductInCartIU.setCartId(4L);   // Cart ID düzenlenmeli
        dtoProductInCartIU.setProductId(6L);    // Product ID düzenlenmeli
        dtoProductInCartIU.setAmount(1);    // Amount düzenlenmeli

        ResponseEntity<DTOCart> dtoCartResponseEntity = restCartController.addProductToCart(dtoProductInCartIU.getCartId(), dtoProductInCartIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dtoCartResponseEntity);
        System.out.println(valueAsString);
    }

    @Test
    void removeProductFromCart() throws JsonProcessingException {
        DTOProductInCartIU dtoProductInCartIU = new DTOProductInCartIU();

        dtoProductInCartIU.setCartId(4L);   // Cart ID düzenlenmeli
        dtoProductInCartIU.setProductId(1L);    // Product ID düzenlenmeli
        dtoProductInCartIU.setAmount(2);    // Amount düzenlenmeli

        ResponseEntity<DTOCart> dtoCartResponseEntity = restCartController.removeProductFromCart(dtoProductInCartIU.getCartId(), dtoProductInCartIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dtoCartResponseEntity);
        System.out.println(valueAsString);
    }

    @Test
    void emptyCart() throws JsonProcessingException {
        Long cartID = 4L;   // Cart ID düzenlenmeli

        ResponseEntity<DTOCart> dtoCart = restCartController.emptyCart(cartID);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dtoCart);
        System.out.println(valueAsString);
    }
}