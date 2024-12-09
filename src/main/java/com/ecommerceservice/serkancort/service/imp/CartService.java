package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInCartIU;
import com.ecommerceservice.serkancort.dto.mapper.CartMapper;
import com.ecommerceservice.serkancort.dto.mapper.ProductMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOCart;
import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.Cart;
import com.ecommerceservice.serkancort.model.ProductInCart;
import com.ecommerceservice.serkancort.repository.CartRepository;
import com.ecommerceservice.serkancort.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ProductService productService;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;



    @Override
    public DTOCart getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cart not found with id " + id));
        cart.calculateTotalPrice();
        Cart response = cartRepository.save(cart);
        return cartMapper.cartToDTO(response);
    }

    public DTOCart getCartByCustomerId(Long customerId) {
        Cart cart = cartRepository.findCartByCustomerId(customerId).orElseThrow(()-> new ResourceNotFoundException("Cart not found with id " + customerId));
        cart.calculateTotalPrice();
        Cart response = cartRepository.save(cart);
        return cartMapper.cartToDTO(response);
    }


    public DTOCart addProductToCart(Long cartId , DTOProductInCartIU request) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart not found with id " + cartId));
        List<ProductInCart> productInCarts = cart.getProducts();
        DTOProduct dtoProduct = productService.getProductById(request.getProductId());
        if (dtoProduct != null && request.getAmount() > 0 && dtoProduct.getIsAvailable()) {
            ProductInCart productInCart1 = productInCarts.stream().filter(productInCart -> productInCart.getProduct().getId().equals(request.getProductId())).findFirst().orElse(null);
            if (productInCart1 == null) {
                productInCart1 = new ProductInCart();
                productInCart1.setCart(cart);
                productInCart1.setProduct(productMapper.productToENTITY(dtoProduct));
                productInCart1.setTotalAmount(request.getAmount());
                productInCarts.add(productInCart1);
                cart.setTotalPrice(productInCart1.getTotalPrice());
            }else {
                productInCart1.setTotalAmount(productInCart1.getTotalAmount() + request.getAmount());
            }
            cart.calculateTotalPrice();
            Cart response = cartRepository.save(cart);
            cart.calculateTotalPrice();
            return cartMapper.cartToDTO(response);
        }else {
            cart.calculateTotalPrice();
            Cart response = cartRepository.save(cart);
            cart.calculateTotalPrice();
            return cartMapper.cartToDTO(response);
        }
    }
    public DTOCart removeProductFromCart(Long cartId , DTOProductInCartIU request) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart not found with id " + cartId));
        List<ProductInCart> productInCarts = cart.getProducts();
        DTOProduct dtoProduct = productService.getProductById(request.getProductId());
        if (dtoProduct != null && request.getAmount() > 0 && dtoProduct.getIsAvailable()) {
            ProductInCart productInCart1 = productInCarts.stream().filter(productInCart -> productInCart.getProduct().getId().equals(request.getProductId())).findFirst().orElse(null);
            if (productInCart1 == null) {
                // istekteki product zaten yok demektir
            }else {
                Integer newAmount = productInCart1.getTotalAmount() - request.getAmount();
                if (newAmount > 0) {
                    productInCart1.setTotalAmount(newAmount);
                }else {
                    productInCarts.remove(productInCart1);
                }
            }
        }
        cart.calculateTotalPrice();
        Cart response = cartRepository.save(cart);
        cart.calculateTotalPrice();
        return cartMapper.cartToDTO(response);
    }

    public DTOCart emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart not found with id " + cartId));
        cart.getProducts().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        Cart response = cartRepository.save(cart);
        return cartMapper.cartToDTO(response);
    }

    public List<DTOCart> getAllCartsByProductNameKeyword(String keyword) {
        List<Cart> carts = cartRepository.findAllCartByProductNameContainingKeyword(keyword);
        return cartMapper.customerToDTOList(carts);
    }

}
