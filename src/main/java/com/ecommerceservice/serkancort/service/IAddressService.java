package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.outward.DTOAddress;
import com.ecommerceservice.serkancort.dto.inward.DTOAddressIU;

public interface IAddressService{
    public DTOAddress createAddress(DTOAddressIU address);
    public DTOAddress getAddressById(Long addressId);
    public DTOAddress updateAddress(Long addressId , DTOAddressIU address);
}
