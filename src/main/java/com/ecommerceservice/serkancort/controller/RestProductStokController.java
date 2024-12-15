package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.outward.DTOProductStok;
import com.ecommerceservice.serkancort.service.imp.ProductStokService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/productStok")
public class RestProductStokController {
    private final ProductStokService productStokService;


    @GetMapping
    public ResponseEntity<List<DTOProductStok>> getAllProductStoks() {
        List<DTOProductStok> productStoks = productStokService.getAllProductStoks();
        return ResponseEntity.ok(productStoks);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<DTOProductStok> getProductStokByProductId(@PathVariable(name = "id") @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long productId) {
        DTOProductStok productStok = productStokService.getProductStokByProductId(productId);
        return ResponseEntity.ok(productStok);
    }

}
