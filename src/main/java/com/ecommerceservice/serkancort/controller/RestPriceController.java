package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOPriceIU;
import com.ecommerceservice.serkancort.dto.outward.DTOPrice;
import com.ecommerceservice.serkancort.service.imp.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api/price")
public class RestPriceController {
    private final PriceService priceService;

    @PostMapping
    public ResponseEntity<DTOPrice> createPrice(@RequestBody DTOPriceIU dtoPrice) {
        DTOPrice price = priceService.createPrice(dtoPrice);
        return ResponseEntity.ok().body(price);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOPrice> getPriceById(@PathVariable Long id) {
        DTOPrice price = priceService.getPriceById(id);
        return ResponseEntity.ok().body(price);
    }
}
