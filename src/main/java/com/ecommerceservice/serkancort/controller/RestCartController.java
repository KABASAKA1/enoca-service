package com.ecommerceservice.serkancort.controller;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInCartIU;
import com.ecommerceservice.serkancort.dto.outward.DTOCart;
import com.ecommerceservice.serkancort.service.imp.CartService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/cart")
public class RestCartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<DTOCart> getCartById(@PathVariable Long id) {
        DTOCart dtoCart = cartService.getCartById(id);
        return ResponseEntity.ok(dtoCart);
    }

    @GetMapping("/{customerId}/byCustomer")
    public ResponseEntity<DTOCart> getCartByCustomerId(@PathVariable Long customerId) {
        DTOCart dtoCart = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(dtoCart);
    }

    @GetMapping("/{keyword}/byProductNameKeyword")
    public ResponseEntity<List<DTOCart>> getCartByProductNameKeyword(@PathVariable String keyword) {
        List<DTOCart> dtoCarts = cartService.getAllCartsByProductNameKeyword(keyword);
        return ResponseEntity.ok(dtoCarts);
    }


    @PutMapping("/{cartId}/addProduct")
    public  ResponseEntity<DTOCart> addProductToCart(@PathVariable Long cartId , @RequestBody DTOProductInCartIU request) {
        DTOCart dtoCart = cartService.addProductToCart(cartId, request);
        return ResponseEntity.ok(dtoCart);
    }

    @PutMapping("/{cartId}/removeProduct")
    public ResponseEntity<DTOCart> removeProductFromCart(@PathVariable Long cartId, @RequestBody DTOProductInCartIU request) {
        DTOCart dtoCart = cartService.removeProductFromCart(cartId, request);
        return ResponseEntity.ok(dtoCart);
    }

    @PostMapping("/{cartId}/emptyCart")
    public ResponseEntity<DTOCart> emptyCart(@PathVariable Long cartId) {
        DTOCart response = cartService.emptyCart(cartId);
        return ResponseEntity.ok(response);
    }

}
