package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInCartIU;
import com.ecommerceservice.serkancort.dto.mapper.ProductInCartMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOProductInCart;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.ProductInCart;
import com.ecommerceservice.serkancort.repository.ProductInCartRepository;
import com.ecommerceservice.serkancort.service.IProductInCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductInCartService implements IProductInCartService {

    private final ProductInCartRepository productInCartRepository;
    private final ProductInCartMapper productInCartMapper;


    @Override
    public DTOProductInCart createProductInCart(DTOProductInCartIU request) {
        ProductInCart productInCart = productInCartMapper.toENTITY(request);
        ProductInCart response = productInCartRepository.save(productInCart);
        DTOProductInCart dtoProductInCart = productInCartMapper.toDTO(response);
        return dtoProductInCart;
    }

    @Override
    public DTOProductInCart getProductInCartById(Long id) {

        ProductInCart response = productInCartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ProductInCart not found for this id :: " + id));
        DTOProductInCart dtoProductInCart = productInCartMapper.toDTO(response);
        return dtoProductInCart;
    }

    @Override
    public DTOProductInCart updateProductInCart(Long id, DTOProductInCartIU request) {
        ProductInCart productInCart = productInCartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ProductInCart not found for this id :: " + id +"  in table of the product in cart "));
        productInCartMapper.updateEntityFromDTO(request,productInCart);
        ProductInCart response = productInCartRepository.save(productInCart);
        DTOProductInCart dtoProductInCart = productInCartMapper.toDTO(response);
        return dtoProductInCart;
    }

    @Override
    public DTOProductInCart getProductInCartByIDs(Long cartId, Long productId) {
        return productInCartRepository.findByCartIdAndProductId(cartId, productId)
                .map(productInCartMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void deleteProductInCartById(Long id) {
        ProductInCart productInCart =productInCartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ProductInCart not found for this id :: " + id));
        productInCartRepository.delete(productInCart);
    }
    public void deleteProductInCartByCartId(Long cartId) {
        productInCartRepository.findById(cartId).ifPresent(productInCartRepository::delete);
    }

}
