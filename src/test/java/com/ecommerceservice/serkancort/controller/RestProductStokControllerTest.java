package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.outward.DTOProductStok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.ecommerceservice.serkancort.starter.EcommerceServiceApplication.class)
class RestProductStokControllerTest {
    @Autowired
    private RestProductStokController restProductStokController;
    @Autowired
    private ObjectMapper objectMapper ;

    @Test
    void getAllProductStoks() throws JsonProcessingException {
        ResponseEntity<List<DTOProductStok>> allProductStoks = restProductStokController.getAllProductStoks();
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allProductStoks);
        System.out.println(valueAsString);
    }

    @Test
    void getProductStokByProductId() throws JsonProcessingException {
        Long productID = 1L;    // Product ID düzenlenmeli

        ResponseEntity<DTOProductStok> productStokByProductId = restProductStokController.getProductStokByProductId(productID);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productStokByProductId);
        System.out.println(valueAsString);
    }
}