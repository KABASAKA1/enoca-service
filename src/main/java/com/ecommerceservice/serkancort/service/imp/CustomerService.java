package com.ecommerceservice.serkancort.service.imp;


import com.ecommerceservice.serkancort.dto.mapper.CustomerMapper;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public DTOCustomer createCustomer(DTOCustomerIU request) {
        return Optional.ofNullable(request)
                    .map(c ->{
                        Customer customer = customerMapper.customerToENTITY(c);
                        customer.setId(null);
                        Cart cart = createEmptyCart();
                        cart.setCustomer(customer);
                        customer.setCart(cart);
                        Customer response = customerRepository.save(customer);
                        return customerMapper.customerToDTO(response);
                    })
                    .orElseThrow(()-> new ResourceNotFoundException("Requested Customer dont must be empty !"));
    }

    @Override
    @Transactional
    public List<DTOCustomer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.customerToDTOList(customers);
    }

    @Override
    @Transactional
    public DTOCustomer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    @Override
    @Transactional
    public DTOCustomer updateCustomer(Long id , DTOCustomerIU request) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerMapper.updateCustomerFromDTO(request, customer);
                    Customer response = customerRepository.save(customer);
                    return customerMapper.customerToDTO(response);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id).ifPresentOrElse(
                customerRepository::delete,
                ()-> {
                    throw new ResourceNotFoundException("Customer not found with id: " + id);
                }
        );
    }

    private Cart createEmptyCart() {
        Cart cart = new Cart();
        cart.setTotalPrice(BigDecimal.ZERO);
        cart.setProducts(new ArrayList<>());
        return cart;
    }
}
