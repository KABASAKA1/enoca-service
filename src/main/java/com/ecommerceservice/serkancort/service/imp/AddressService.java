package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.mapper.AddressMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOAddress;
import com.ecommerceservice.serkancort.dto.inward.DTOAddressIU;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.Address;
import com.ecommerceservice.serkancort.repository.AddressRepository;
import com.ecommerceservice.serkancort.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public DTOAddress createAddress(DTOAddressIU request) {
        return Optional.ofNullable(request)
                .map(r-> {
                    r.setId(null);
                    Address address = addressMapper.addressToENTITY(r);
                    Address saved = addressRepository.save(address);
                    return addressMapper.addressToDTO(saved);
                })
                .orElseThrow( () -> new ResourceNotFoundException("Request address dont must be empty!"));
    }

    @Override
    public DTOAddress getAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .map(addressMapper::addressToDTO)
                .orElseThrow( () -> new ResourceNotFoundException("Address not found with id: " + addressId ));
    }

    @Override
    public DTOAddress updateAddress(Long addressId , DTOAddressIU request) {
        return addressRepository.findById(addressId)
                .map(address -> {
                    addressMapper.updateAddressFromDTO(request, address);
                    Address saved = addressRepository.save(address);
                    return addressMapper.addressToDTO(saved);
                })
                .orElseThrow( () -> new ResourceNotFoundException("Address not found with id: " + addressId ));
    }
}
