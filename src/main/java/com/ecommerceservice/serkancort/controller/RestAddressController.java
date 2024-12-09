package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOAddressIU;
import com.ecommerceservice.serkancort.dto.outward.DTOAddress;
import com.ecommerceservice.serkancort.service.imp.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api/address")
public class RestAddressController {
    private final AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<DTOAddress> getAddressById(@PathVariable Long id){
        DTOAddress dtoAddress = addressService.getAddressById(id);
        return ResponseEntity.ok().body(dtoAddress);
    }

    @PostMapping
    public ResponseEntity<DTOAddress> createAddress(@RequestBody DTOAddressIU request){
        DTOAddress dtoAddress = addressService.createAddress(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOAddress> updateAddress(@PathVariable Long id , @RequestBody DTOAddressIU request){
        DTOAddress dtoAddress = addressService.updateAddress(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(dtoAddress);
    }
}
