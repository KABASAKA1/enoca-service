package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOProductIU;
import com.ecommerceservice.serkancort.dto.mapper.ProductMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.Price;
import com.ecommerceservice.serkancort.model.Product;
import com.ecommerceservice.serkancort.model.ProductStok;
import com.ecommerceservice.serkancort.repository.PriceRepository;
import com.ecommerceservice.serkancort.repository.ProductRepository;
import com.ecommerceservice.serkancort.repository.ProductStokRepository;
import com.ecommerceservice.serkancort.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PriceRepository priceRepository;
    private final ProductStokRepository productStokRepository;

    @Transactional
    @Override
    public DTOProduct createPruduct(DTOProductIU request) {
        request.setId(null);
        Product product = new Product();
        product.setName(request.getName());

        Price price = new Price();
        price.setPrice(request.getPrice());

        ProductStok productStok = new ProductStok();
        productStok.setProduct(product);
        productStok.setStokAdet(request.getStokAdet());
        List<Price> priceHistory = new ArrayList<>();
        priceHistory.add(price);
        productStok.setPriceHistory(priceHistory);
        boolean isAvailable = request.getStokAdet()!=null && request.getStokAdet().compareTo(BigDecimal.ZERO)>0;

        price.setProductStok(productStok);
        product.setPrice(price);
        product.setProductStok(productStok);
        product.setIsAvailable(isAvailable);

        Product response = productRepository.save(product);
        return productMapper.productToDTO(response);
    }

    @Transactional
    public DTOProduct updateProductPrice(Long id, DTOProductIU request) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id: " + id));
        ProductStok productStok = product.getProductStok();
        Price price = new Price();

        price.setPrice(request.getPrice());
        price.setProductStok(productStok);
        productStok.getPriceHistory().add(price);
        product.setPrice(price);
        product.setProductStok(productStok);

        Product response = productRepository.save(product);
        return productMapper.productToDTO(response);
    }
    @Transactional
    public DTOProduct updateProductStock(Long id , DTOProductIU request) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found with id: " + id));
        ProductStok productStok = product.getProductStok();

        productStok.setStokAdet(request.getStokAdet());
        product.setProductStok(productStok);

        Product response = productRepository.save(product);
        return productMapper.productToDTO(response);
    }


    @Transactional
    @Override
    public List<DTOProduct> getAllPruducts() {

        List<Product> products = productRepository.findAll();
        List<DTOProduct> dtoProducts = productMapper.productToDTOList(products);
        return dtoProducts;
    }

    @Transactional
    @Override
    public DTOProduct getProductById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException( "Product not found with id: " + id));
        DTOProduct dtoProduct = productMapper.productToDTO(product);
        return dtoProduct;
    }


    @Transactional
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException( "Product not found with id: " + id));
        productRepository.delete(product);
    }
}
