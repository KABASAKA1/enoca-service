package com.ecommerceservice.serkancort;

import com.ecommerceservice.serkancort.controller.*;
import com.ecommerceservice.serkancort.dto.outward.DTOCustomer;
import com.ecommerceservice.serkancort.starter.EcommerceServiceApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(classes = com.ecommerceservice.serkancort.starter.EcommerceServiceApplication.class)
class EcommerceServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
