package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOAddressIU;
import com.ecommerceservice.serkancort.dto.inward.DTOCartIU;
import com.ecommerceservice.serkancort.dto.mapper.CustomerMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOAddress;
import com.ecommerceservice.serkancort.dto.outward.DTOCart;
import com.ecommerceservice.serkancort.dto.outward.DTOCustomer;
import com.ecommerceservice.serkancort.dto.inward.DTOCustomerIU;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.Cart;
import com.ecommerceservice.serkancort.model.Customer;
import com.ecommerceservice.serkancort.repository.CustomerRepository;
import com.ecommerceservice.serkancort.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final AddressService addressService;
    private final CartService cartService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public DTOCustomer createCustomer(DTOCustomerIU request) {
        request.setId(null);
        Customer customer = customerMapper.customerToENTITY(request);
        Cart cart = new Cart();
        cart.setProducts(new ArrayList<>());
        cart.setTotalPrice(BigDecimal.ZERO);
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer response = customerRepository.save(customer);
        DTOCustomer dtoCustomer = customerMapper.customerToDTO(response);
        return dtoCustomer;
    }

    @Override
    @Transactional
    public List<DTOCustomer> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        List<DTOCustomer> dtoCustomers = customerMapper.customerToDTOList(customers);
        return dtoCustomers;
    }

    @Override
    @Transactional
    public DTOCustomer getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found with id: " + id));
        DTOCustomer dtoCustomer = customerMapper.customerToDTO(customer);
        return dtoCustomer;
    }

    @Override
    public DTOCustomer updateCustomer(Long id , DTOCustomerIU request) {

        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found with id: " + id));
        customerMapper.updateCustomerFromDTO(request, customer);
        Customer response = customerRepository.save(customer);
        DTOCustomer dtoCustomer = customerMapper.customerToDTO(response);
        return dtoCustomer;
    }

    @Override
    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
}
