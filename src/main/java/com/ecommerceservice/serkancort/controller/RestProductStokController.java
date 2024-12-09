package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.outward.DTOProductStok;
import com.ecommerceservice.serkancort.service.imp.ProductStokService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api/productStok")
public class RestProductStokController {
    private final ProductStokService productStokService;


    @GetMapping
    public ResponseEntity<List<DTOProductStok>> getAllProductStoks() {
        List<DTOProductStok> productStoks = productStokService.getAllProductStoks();
        return ResponseEntity.ok(productStoks);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<DTOProductStok> getProductStokByProductId(@PathVariable(name = "id") Long productId) {
        DTOProductStok productStok = productStokService.getProductStokByProductId(productId);
        return ResponseEntity.ok(productStok);
    }

}
