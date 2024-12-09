package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOPriceIU;
import com.ecommerceservice.serkancort.dto.mapper.PriceMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOPrice;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.Price;
import com.ecommerceservice.serkancort.repository.PriceRepository;
import com.ecommerceservice.serkancort.service.IPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService implements IPriceService {
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public DTOPrice createPrice(DTOPriceIU request) {
        request.setId(null);
        Price price = priceMapper.priceToENTITY(request);
        Price response = priceRepository.save(price);
        DTOPrice dtoPrice = priceMapper.priceToDTO(response);
        return dtoPrice;
    }

    @Override
    public DTOPrice getPriceById(Long id) {
        Price price = priceRepository.findById(id).orElse(null);
        DTOPrice dtoPrice = priceMapper.priceToDTO(price);
        return dtoPrice;
    }

}
