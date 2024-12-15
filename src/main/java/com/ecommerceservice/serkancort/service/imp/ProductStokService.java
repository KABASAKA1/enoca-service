package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOProductIU;
import com.ecommerceservice.serkancort.dto.inward.DTOProductStokIU;
import com.ecommerceservice.serkancort.dto.mapper.PriceMapper;
import com.ecommerceservice.serkancort.dto.mapper.ProductMapper;
import com.ecommerceservice.serkancort.dto.mapper.ProductStokMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOProductStok;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.Price;
import com.ecommerceservice.serkancort.model.Product;
import com.ecommerceservice.serkancort.model.ProductStok;
import com.ecommerceservice.serkancort.repository.ProductStokRepository;
import com.ecommerceservice.serkancort.service.IProductStokService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductStokService implements IProductStokService {

    private final ProductStokRepository productStokRepository;
    private final ProductStokMapper productStokMapper;

    public List<DTOProductStok> getAllProductStoks() {
        List<ProductStok> productStoks = productStokRepository.findAll();
        productStoks.forEach(this::updateInStockStatus);
        return productStokMapper.productStokToDTOList(productStoks);
    }

    public DTOProductStok getProductStokByProductId(Long productId) {
        return productStokRepository.findByProductId(productId)
                .map(this::updateInStockStatus)
                .map(productStokMapper::productStokToDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Product stok not found for product id: " + productId));
    }


    protected ProductStok updateInStockStatus(ProductStok productStok) {
        productStok.getProduct().setIsAvailable(calculateInStockStatus(productStok));
        return productStokRepository.save(productStok);
    }
    private Boolean calculateInStockStatus(ProductStok productStok) {
        return Optional.ofNullable(productStok)
                .map(ProductStok::getStokAdet)
                .map(stock -> stock.compareTo(BigDecimal.ZERO) > 0)
                .orElse(false);
    }

}
