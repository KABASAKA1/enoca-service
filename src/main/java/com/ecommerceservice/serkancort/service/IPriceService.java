package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.outward.DTOPrice;
import com.ecommerceservice.serkancort.dto.inward.DTOPriceIU;

public interface IPriceService {
    public DTOPrice createPrice(DTOPriceIU price);
    public DTOPrice getPriceById(Long id);
}
