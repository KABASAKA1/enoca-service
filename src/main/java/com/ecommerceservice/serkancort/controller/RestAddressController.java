package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOAddressIU;
import com.ecommerceservice.serkancort.dto.outward.DTOAddress;
import com.ecommerceservice.serkancort.service.imp.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/address")
public class RestAddressController {
    private final AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<DTOAddress> getAddressById(@PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long id){
        DTOAddress dtoAddress = addressService.getAddressById(id);
        return ResponseEntity.ok().body(dtoAddress);
    }

    @PostMapping
    public ResponseEntity<DTOAddress> createAddress(@Valid @RequestBody DTOAddressIU request){
        DTOAddress dtoAddress = addressService.createAddress(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOAddress> updateAddress(@PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long id , @Valid @RequestBody DTOAddressIU request){
        DTOAddress dtoAddress = addressService.updateAddress(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(dtoAddress);
    }
}
