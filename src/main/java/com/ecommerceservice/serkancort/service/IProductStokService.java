package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.outward.DTOPrice;
import com.ecommerceservice.serkancort.dto.outward.DTOProductStok;
import com.ecommerceservice.serkancort.dto.inward.DTOProductStokIU;

import java.util.List;

public interface IProductStokService {
    public List<DTOProductStok> getAllProductStoks();
    public DTOProductStok getProductStokByProductId(Long productId);
}
