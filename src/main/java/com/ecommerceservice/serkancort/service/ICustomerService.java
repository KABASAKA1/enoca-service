package com.ecommerceservice.serkancort.service;

import com.ecommerceservice.serkancort.dto.outward.DTOCustomer;
import com.ecommerceservice.serkancort.dto.inward.DTOCustomerIU;

import java.util.List;

public interface ICustomerService {
    public DTOCustomer createCustomer(DTOCustomerIU customer);
    public List<DTOCustomer> getAllCustomers();
    public DTOCustomer getCustomerById(Long id);
    public DTOCustomer updateCustomer(Long id , DTOCustomerIU customer);
    public void deleteCustomer(Long id);
}
