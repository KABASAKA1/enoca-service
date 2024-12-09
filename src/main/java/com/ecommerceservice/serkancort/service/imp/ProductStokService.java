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

@Service
@RequiredArgsConstructor
public class ProductStokService implements IProductStokService {

    private final ProductStokRepository productStokRepository;
    private final ProductStokMapper productStokMapper;

    public List<DTOProductStok> getAllProductStoks() {
        List<ProductStok> productStoks = productStokRepository.findAll();
        return productStokMapper.productStokToDTOList(productStoks);
    }

    @Override
    public DTOProductStok getProductStok(Long id) {
        ProductStok response = productStokRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Stok not found with id" + id));
        DTOProductStok dtoProductStok = productStokMapper.productStokToDTO(response);
        return dtoProductStok;
    }

    public DTOProductStok getProductStokByProductId(Long productId) {
        ProductStok productStok = productStokRepository.findByProductId(productId).orElseThrow(()-> new ResourceNotFoundException("Product Stok not found with id" + productId));
        return productStokMapper.productStokToDTO(productStok);
    }


}
