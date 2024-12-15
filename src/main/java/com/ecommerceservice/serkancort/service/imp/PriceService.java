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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService implements IPriceService {
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public DTOPrice createPrice(DTOPriceIU request) {
        return Optional.ofNullable(request)
                        .map(p->{
                            Price price =priceMapper.priceToENTITY(p);
                            price.setId(null);
                            return priceMapper.priceToDTO(price);
                        }).orElseThrow(()-> new ResourceNotFoundException("Price request dont must be empty"));
    }

    @Override
    public DTOPrice getPriceById(Long id) {
        return priceRepository.findById(id)
                .map(priceMapper::priceToDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Price not found for id : " + id));
    }

}
