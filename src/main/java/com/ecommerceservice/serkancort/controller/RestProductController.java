package com.ecommerceservice.serkancort.controller;


import com.ecommerceservice.serkancort.dto.inward.DTOProductIU;
import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.ecommerceservice.serkancort.service.imp.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/api/product")
@RequiredArgsConstructor
public class RestProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<DTOProduct>> getAllProducts() {
        List<DTOProduct> products = productService.getAllPruducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOProduct> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<DTOProduct> createProduct(@RequestBody DTOProductIU request) {
        DTOProduct product = productService.createPruduct(request);
        return ResponseEntity.ok().body(product);
    }
    @PutMapping("/price/{id}")
    public ResponseEntity<DTOProduct> updateProductPrice(@PathVariable Long id, @RequestBody DTOProductIU request) {
        DTOProduct product = productService.updateProductPrice(id, request);
        return ResponseEntity.ok().body(product);
    }
    @PutMapping("/stock/{id}")
    public ResponseEntity<DTOProduct> updateProductStock(@PathVariable Long id, @RequestBody DTOProductIU request) {
        DTOProduct product = productService.updateProductStock(id, request);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
