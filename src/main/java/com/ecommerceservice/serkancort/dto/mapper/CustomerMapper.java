package com.ecommerceservice.serkancort.dto.mapper;

import com.ecommerceservice.serkancort.dto.inward.DTOCustomerIU;
import com.ecommerceservice.serkancort.dto.outward.DTOCustomer;
import com.ecommerceservice.serkancort.model.Customer;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring" ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    DTOCustomer customerToDTO(Customer customer);
   // @Mapping(source = "cartId",target = "cart.id")
    Customer customerToENTITY(DTOCustomerIU dtoCustomer);
    List<DTOCustomer> customerToDTOList(List<Customer> customers);
    void updateCustomerFromDTO( DTOCustomerIU dtoCustomer ,@MappingTarget Customer customer);

}
