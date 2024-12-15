package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOAddressIU;
import com.ecommerceservice.serkancort.dto.inward.DTOCustomerIU;
import com.ecommerceservice.serkancort.dto.outward.DTOAddress;
import com.ecommerceservice.serkancort.dto.outward.DTOCustomer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.ecommerceservice.serkancort.starter.EcommerceServiceApplication.class)
class RestCustomerControllerTest {
    @Autowired
    private RestCustomerController restCustomerController;
    @Autowired
    private ObjectMapper objectMapper ;

    @Test
    void getAllCustomer() throws JsonProcessingException {
        ResponseEntity<List<DTOCustomer>> allCustomer = restCustomerController.getAllCustomer();
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allCustomer);
        System.out.println(valueAsString);
    }

    @Test
    void getCustomerById() throws JsonProcessingException {
        Long customerID = 9L;  // customer ID düzenlenmeli

        ResponseEntity<DTOCustomer> customerById = restCustomerController.getCustomerById(customerID);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerById);
        System.out.println(valueAsString);
    }

    @Test
    void createCustomer() throws JsonProcessingException {
        DTOAddressIU
                dtoAddressIU = new DTOAddressIU(null,"Adana","Demirören","Bahçıvan","seyid");
                // address alanı düzenlenmeli
        DTOCustomerIU
                dtoCustomerIU = new DTOCustomerIU(null,"Funda","5377077535",dtoAddressIU);
                // customer alanı düzenlenmeli

        ResponseEntity<DTOCustomer> customerById = restCustomerController.createCustomer(dtoCustomerIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerById);
        System.out.println(valueAsString);
    }

    @Test
    void updateCustomer() throws JsonProcessingException {
        DTOAddressIU
                dtoAddressIU = new DTOAddressIU(null,"Muğla","Demirören","Bahçıvan","seyid");
                // address alanı düzenlenmeli
        dtoAddressIU.setId(10L);  // Address ID düzenlenmeli
        DTOCustomerIU
                dtoCustomerIU = new DTOCustomerIU(null,"Funda","5377077535",dtoAddressIU);
                // customer alanı düzenlenmeli
        dtoCustomerIU.setId(9L);  //Customer ID düzenlenmeli

        ResponseEntity<DTOCustomer> customerById = restCustomerController.updateCustomer(dtoCustomerIU.getId(),dtoCustomerIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerById);
        System.out.println(valueAsString);
    }

    @Test
    void deleteCustomer() {
        Long customerID = 4L;   // Customer ID düzenlenmeli

        restCustomerController.deleteCustomer(customerID);
    }
}