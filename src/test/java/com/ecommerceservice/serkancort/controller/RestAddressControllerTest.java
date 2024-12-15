package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOAddressIU;
import com.ecommerceservice.serkancort.dto.outward.DTOAddress;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = com.ecommerceservice.serkancort.starter.EcommerceServiceApplication.class)
class RestAddressControllerTest {
    @Autowired
    private RestAddressController restAddressController;
    @Autowired
    private ObjectMapper objectMapper ;

    @Test
    void getAddressById() throws JsonProcessingException {
        Long addressId = 1L;    // Address ID düzenlenmeli

        ResponseEntity<DTOAddress> address = restAddressController.getAddressById(addressId);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address);
        System.out.println(valueAsString);

    }

    @Test
    void createAddress() throws JsonProcessingException {
        DTOAddressIU
                dtoAddressIU = new DTOAddressIU(null,"Adana","Demirören","Bahçıvan","seyid");
                //address alanı düzenlenmeli

        ResponseEntity<DTOAddress> address = restAddressController.createAddress(dtoAddressIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address);
        System.out.println(valueAsString);
    }

    @Test
    void updateAddress() throws JsonProcessingException {
        DTOAddressIU
                dtoAddressIU = new DTOAddressIU(null,"Adana","Demirören","Bahçıvan","seyid");
                // address alanı düzenlenmeli
        dtoAddressIU.setId(1L); // ID düzenlenmeli

        ResponseEntity<DTOAddress> address = restAddressController.updateAddress(dtoAddressIU.getId(),dtoAddressIU);
        String valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address);
        System.out.println(valueAsString);
    }
}


