package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOProductIU;
import com.ecommerceservice.serkancort.dto.mapper.ProductMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOProduct;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.Price;
import com.ecommerceservice.serkancort.model.Product;
import com.ecommerceservice.serkancort.model.ProductStok;
import com.ecommerceservice.serkancort.repository.ProductRepository;
import com.ecommerceservice.serkancort.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public DTOProduct createPruduct(DTOProductIU request) {
        return Optional.ofNullable(request)
                        .map(p ->{
                            Product product = new Product();
                            product.setName(p.getName());

                            Price price = new Price();
                            price.setPrice(p.getPrice());

                            ProductStok productStok = new ProductStok();
                            productStok.setProduct(product);
                            productStok.setStokAdet(p.getStokAdet());

                            List<Price> priceHistory = new ArrayList<>();
                            priceHistory.add(price);
                            productStok.setPriceHistory(priceHistory);
                            boolean isAvailable = p.getStokAdet()!=null && p.getStokAdet().compareTo(BigDecimal.ZERO)>0;

                            price.setProductStok(productStok);
                            product.setPrice(price);
                            product.setProductStok(productStok);
                            product.setIsAvailable(isAvailable);

                            product = updateInStockStatus(product);
                            Product response = productRepository.save(product);
                            return productMapper.productToDTO(response);
                        })
                        .orElseThrow(()-> new ResourceNotFoundException("Product request dont must be empty"));
    }

    @Transactional
    @Override
    public DTOProduct updateProductPrice(Long id, DTOProductIU request) {
        if (request.getPrice() == null) {
            throw new ResourceNotFoundException("Product price request dont must be empty");
        }
        return productRepository.findById(id)
                .map(product -> {
                    ProductStok productStok = product.getProductStok();
                    Price price = createPrice(productStok, request.getPrice());
                    productStok.getPriceHistory().add(price);
                    product.setPrice(price);
                    product = updateInStockStatus(product);
                    Product response = productRepository.save(product);
                    return productMapper.productToDTO(response);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Product  not found with id:: "+id));
    }
    @Transactional
    @Override
    public DTOProduct updateProductStock(Long id , DTOProductIU request) {
        if (request.getStokAdet() == null) {
            throw new ResourceNotFoundException("Product stok request dont must be empty");
        }
        return productRepository.findById(id)
                .map(product -> {
                    ProductStok productStok = product.getProductStok();
                    productStok.setStokAdet(request.getStokAdet());
                    product.setProductStok(productStok);
                    product = updateInStockStatus(product);
                    Product response = productRepository.save(product);
                    return productMapper.productToDTO(response);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with id :: "+id));
    }


    @Transactional
    @Override
    public List<DTOProduct> getAllPruducts() {

        List<Product> products = productRepository.findAll();
        products.forEach(this::updateInStockStatus);
        List<Product> response = productRepository.saveAll(products);
        return productMapper.productToDTOList(response);
    }

    @Transactional
    @Override
    public DTOProduct getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product = productRepository.save(updateInStockStatus(product));
                    return productMapper.productToDTO(product);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with id:: "+id));
    }


    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        productRepository::delete,
                        ()-> {throw  new ResourceNotFoundException("Product not found with id:: "+id);}
                );
    }


    private Price createPrice(ProductStok productStok , BigDecimal price) {
        Price priceEntity = new Price();
        priceEntity.setPrice(price);
        priceEntity.setProductStok(productStok);
        return priceEntity;
    }

    protected Product updateInStockStatus(Product product) {
        product.setIsAvailable(calculateInStockStatus(product));
        return product;
    }
    private Boolean calculateInStockStatus(Product product) {
        return Optional.ofNullable(product)
                .map(Product::getProductStok)
                .map(ProductStok::getStokAdet)
                .map(stock -> stock.compareTo(BigDecimal.ZERO) > 0)
                .orElse(false);
    }

}
