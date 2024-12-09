package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOProductInOrderIU;
import com.ecommerceservice.serkancort.dto.mapper.ProductInOrderMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOProductInOrder;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.ProductInOrder;
import com.ecommerceservice.serkancort.repository.ProductInOrderRepository;
import com.ecommerceservice.serkancort.service.IProductInOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInOrderService implements IProductInOrderService {

    private final ProductInOrderRepository productInOrderRepository;
    private final ProductInOrderMapper productInOrderMapper;


    @Override
    public DTOProductInOrder createProductInOrder(DTOProductInOrderIU request) {
        request.setId(null);
        ProductInOrder productInOrder = productInOrderMapper.toENTITY(request);
        ProductInOrder response = productInOrderRepository.save(productInOrder);
        DTOProductInOrder dtoProductInOrder = productInOrderMapper.toDTO(response);
        return dtoProductInOrder;
    }

    @Override
    public DTOProductInOrder getProductInOrderById(Long id) {

        ProductInOrder response = productInOrderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ProductInOrder not found for this id :: " + id));
        DTOProductInOrder dtoProductInOrder = productInOrderMapper.toDTO(response);
        return dtoProductInOrder;
    }

    @Override
    public DTOProductInOrder updateProductInOrder(Long id, DTOProductInOrderIU request) {

        ProductInOrder productInOrder = productInOrderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ProductInOrder not found for this id :: " + id));
        productInOrderMapper.updateEntityFromDTO(request,productInOrder);
        ProductInOrder response = productInOrderRepository.save(productInOrder);
        DTOProductInOrder dtoProductInOrder = productInOrderMapper.toDTO(response);
        return dtoProductInOrder;
    }

}
