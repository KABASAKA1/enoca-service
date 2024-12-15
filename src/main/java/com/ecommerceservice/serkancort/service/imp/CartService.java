package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInCartIU;
import com.ecommerceservice.serkancort.dto.mapper.CartMapper;
import com.ecommerceservice.serkancort.dto.mapper.ProductMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOCart;
import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.Cart;
import com.ecommerceservice.serkancort.model.Product;
import com.ecommerceservice.serkancort.model.ProductInCart;
import com.ecommerceservice.serkancort.repository.CartRepository;
import com.ecommerceservice.serkancort.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final ProductService productService;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;



    @Override
    public DTOCart getCartById(Long id) {
        return cartRepository.findById(id)
                .map(cart -> {
                    Cart updated = calculateTotalPrice(cart);
                    Cart response = cartRepository.save(updated);
                    return cartMapper.cartToDTO(response);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Cart not found with id " + id));
    }

    public DTOCart getCartByCustomerId(Long customerId) {
        return cartRepository.findCartByCustomerId(customerId)
                .map(cart -> {
                    Cart updated = calculateTotalPrice(cart);
                    Cart response = cartRepository.save(updated);
                    return cartMapper.cartToDTO(response);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Cart not found with customer id " + customerId));
    }


    public DTOCart addProductToCart(Long cartId , DTOProductInCartIU request) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));
        DTOProduct dtoProduct = productService.getProductById(request.getProductId());

        if (dtoProduct == null || request.getAmount() <= 0 || !dtoProduct.getIsAvailable()) {
            return cartMapper.cartToDTO(cartRepository.save(cart));
        }

        cart.getProducts().stream()
                .filter(productInCart -> productInCart.getProduct().getId().equals(request.getProductId()))
                .findFirst()
                .map(productInCart -> {
                    productInCart.setTotalAmount(productInCart.getTotalAmount() + request.getAmount());
                    return productInCart;
                })
                .orElseGet(() -> {
                    ProductInCart newProductInCart = createProductInCart(
                            cart,
                            productMapper.productToENTITY(dtoProduct),
                            request.getAmount()
                    );
                    cart.getProducts().add(newProductInCart);
                    return newProductInCart;
                });
        Cart response = cartRepository.save(cart);
        response = calculateTotalPrice(response);
        return cartMapper.cartToDTO(cartRepository.save(response));
    }
    public DTOCart removeProductFromCart(Long cartId , DTOProductInCartIU request) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));
        DTOProduct dtoProduct = productService.getProductById(request.getProductId());

        if (dtoProduct == null || request.getAmount() <= 0 || !dtoProduct.getIsAvailable()) {
            return cartMapper.cartToDTO(cartRepository.save(cart));
        }

        cart.getProducts().stream()
                .filter(productInCart -> productInCart.getProduct().getId().equals(request.getProductId()))
                .findFirst()
                .ifPresent(productInCart -> {
                    int newAmount = productInCart.getTotalAmount() - request.getAmount();
                    if (newAmount > 0) {
                        productInCart.setTotalAmount(newAmount);
                    } else {
                        cart.getProducts().remove(productInCart);
                    }
                });
        Cart response = cartRepository.save(cart);
        response = calculateTotalPrice(response);
        return cartMapper.cartToDTO(cartRepository.save(response));
    }

    public DTOCart emptyCart(Long cartId) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.getProducts().clear();
                    cart.setTotalPrice(BigDecimal.ZERO);
                    Cart response = cartRepository.save(cart);
                    return cartMapper.cartToDTO(response);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Cart not found with id : " + cartId));
    }

    public List<DTOCart> getAllCartsByProductNameKeyword(String keyword) {
        return cartRepository.findAllCartByProductNameContainingKeyword(keyword)
                .map(cartMapper::cartToDTOList)
                .orElseThrow(()-> new ResourceNotFoundException("It's not found carts that given product name keyword"));
    }

    protected void orderPlace(Cart cart) {
        updateStockAfterOrderPlace(cart);
        cart.getProducts().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    private void updateStockAfterOrderPlace(Cart cart) {
        cart.getProducts().forEach(product -> {
            Integer totalAmount = product.getTotalAmount();
            BigDecimal stokAdet = product.getProduct().getProductStok().getStokAdet();
            BigDecimal newStokAdet = stokAdet.subtract(BigDecimal.valueOf(totalAmount));
            product.getProduct().getProductStok().setStokAdet(newStokAdet);
        });
    }


    private ProductInCart createProductInCart(Cart cart, Product product , Integer amount) {
        ProductInCart productInCart = new ProductInCart();
        productInCart.setCart(cart);
        productInCart.setProduct(product);
        productInCart.setTotalAmount(amount);
        return productInCart;
    }

    private Cart calculateTotalPrice(Cart cart) {
        Optional.ofNullable(cart)
                .map(cart1 -> {
                    Integer totalPrice = Optional.ofNullable(cart1.getProducts())
                            .stream()
                            .flatMap(List::stream)
                            .map(productInCart -> Optional.ofNullable(productInCart.getTotalAmount()).orElse(0))
                            .reduce(0,Integer::sum);
                    cart1.setTotalPrice(BigDecimal.valueOf(totalPrice));
                    return cart1;
                })
                .orElseThrow(()-> new ResourceNotFoundException("Cart not found for calculate price !"));
        return cart;
    }

}
