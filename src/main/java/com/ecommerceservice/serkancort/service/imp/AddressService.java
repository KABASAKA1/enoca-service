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

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public DTOAddress createAddress(DTOAddressIU request) {
        if (request == null) {
            request = new DTOAddressIU();
            Address address = addressMapper.addressToENTITY(request);
            addressRepository.save(address);
            return addressMapper.addressToDTO(address);
        }


        request.setId(null);
        Address address = addressMapper.addressToENTITY(request);
        address = addressRepository.save(address);
        DTOAddress dtoAddress = addressMapper.addressToDTO(address);
        return dtoAddress;
    }

    @Override
    public DTOAddress getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address not found with id : " + addressId));

        DTOAddress dtoAddress = addressMapper.addressToDTO(address);
        return dtoAddress;
    }

    @Override
    public DTOAddress updateAddress(Long addressId , DTOAddressIU request) {
        Address addressToUpdate = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found with id : " + addressId));
        addressMapper.updateAddressFromDTO(request, addressToUpdate);
        addressRepository.save(addressToUpdate);
        DTOAddress dtoAddress = addressMapper.addressToDTO(addressToUpdate);
        return dtoAddress;
    }
}
