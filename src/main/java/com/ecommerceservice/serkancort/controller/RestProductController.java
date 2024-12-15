package com.ecommerceservice.serkancort.controller;


import com.ecommerceservice.serkancort.dto.inward.DTOProductIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.ecommerceservice.serkancort.service.imp.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/product")
public class RestProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<DTOProduct>> getAllProducts() {
        List<DTOProduct> products = productService.getAllPruducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOProduct> getProductById(@PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<DTOProduct> createProduct( @Valid @RequestBody DTOProductIU request) {
        DTOProduct product = productService.createPruduct(request);
        return ResponseEntity.ok().body(product);
    }
    @PutMapping("/price/{id}")
    public ResponseEntity<DTOProduct> updateProductPrice(@PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long id, @Valid @RequestBody DTOProductIU request) {
        DTOProduct product = productService.updateProductPrice(id, request);
        return ResponseEntity.ok().body(product);
    }
    @PutMapping("/stock/{id}")
    public ResponseEntity<DTOProduct> updateProductStock(@PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long id, @Valid @RequestBody DTOProductIU request) {
        DTOProduct product = productService.updateProductStock(id, request);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Min(value = 1 , message = "Geçerli bir ID giriniz!") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
