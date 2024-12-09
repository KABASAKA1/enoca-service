package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOAddressIU;
import com.ecommerceservice.serkancort.dto.outward.DTOAddress;
import com.ecommerceservice.serkancort.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {

    DTOAddress addressToDTO(Address address);
    Address addressToENTITY(DTOAddressIU dtoAddress);
    void updateAddressFromDTO( DTOAddressIU dtoAddress ,@MappingTarget Address address);

}
