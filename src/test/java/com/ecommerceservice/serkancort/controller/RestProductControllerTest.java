package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOProductIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = com.ecommerceservice.serkancort.starter.EcommerceServiceApplication.class)
class RestProductControllerTest {
    @Autowired
    private RestProductController restProductController;
    @Autowired
    private ObjectMapper objectMapper ;

    @Test
    void getAllProducts() throws JsonProcessingException {
        ResponseEntity<List<DTOProduct>> allProducts = restProductController.getAllProducts();
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allProducts));
    }

    @Test
    void getProductById() throws JsonProcessingException {
        Long productID = 1L;    // Product ID düzenlenmeli

        ResponseEntity<DTOProduct> productById = restProductController.getProductById(productID);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productById);
        System.out.println(valueAsString);
    }

    @Test
    void createProduct() throws JsonProcessingException {
        DTOProductIU dtoProductIU = new DTOProductIU();

        dtoProductIU.setName("Laptop"); // Product name düzenlenmeli
        dtoProductIU.setPrice(BigDecimal.valueOf(321)); // Product price düzenlenmeli
        dtoProductIU.setStokAdet(BigDecimal.valueOf(100));  // Product stok adet düzenlenmeli

        ResponseEntity<DTOProduct> product = restProductController.createProduct(dtoProductIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(product);
        System.out.println(valueAsString);

    }

    @Test
    void updateProductPrice() throws JsonProcessingException {
        DTOProductIU dtoProductIU = new DTOProductIU();

        dtoProductIU.setId(1L); // Product ID düzenlenmeli
        dtoProductIU.setName("Erik"); // Product name düzenlenmeli
        dtoProductIU.setPrice(BigDecimal.valueOf(55));  // Product price düzenlenmeli
        dtoProductIU.setStokAdet(BigDecimal.valueOf(20));  // Product stok adet düzenlenmeli

        ResponseEntity<DTOProduct> productResponseEntity = restProductController.updateProductPrice(dtoProductIU.getId(), dtoProductIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productResponseEntity);
        System.out.println(valueAsString);
    }

    @Test
    void updateProductStock() throws JsonProcessingException {
        DTOProductIU dtoProductIU = new DTOProductIU();

        dtoProductIU.setId(1L); // Product ID düzenlenmeli
        dtoProductIU.setName("Erik"); // Product name girilmeli validations için
        dtoProductIU.setPrice(BigDecimal.valueOf(55));  // Product price girilmeli validations için
        dtoProductIU.setStokAdet(BigDecimal.valueOf(20));  // Product stok adet düzenlenmeli

        ResponseEntity<DTOProduct> productResponseEntity = restProductController.updateProductStock(dtoProductIU.getId(), dtoProductIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productResponseEntity);
        System.out.println(valueAsString);
    }

    @Test
    void deleteProduct() {
        Long productID = 1L;    // Product ID düzenlenmeli

        restProductController.deleteProduct(productID);
    }
}