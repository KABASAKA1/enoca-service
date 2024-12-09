package com.ecommerceservice.serkancort.controller;


import com.ecommerceservice.serkancort.dto.inward.DTOCustomerIU;
import com.ecommerceservice.serkancort.dto.outward.DTOCustomer;
import com.ecommerceservice.serkancort.service.imp.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api/customer")
public class RestCustomerController {
    private final CustomerService customerService;


    @GetMapping
    public ResponseEntity<List<DTOCustomer>> getAllCustomer(){
        List<DTOCustomer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOCustomer> getCustomerById(@PathVariable Long id){
        DTOCustomer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<DTOCustomer> createCustomer(@RequestBody DTOCustomerIU request){
        DTOCustomer customer = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DTOCustomer> updateCustomer(@PathVariable Long id, @RequestBody DTOCustomerIU request){
        DTOCustomer customer = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
