package com.ecommerceservice.serkancort.service.imp;

import com.ecommerceservice.serkancort.dto.inward.DTOCustomerIU;
import com.ecommerceservice.serkancort.dto.inward.DTOOrderIU;
import com.ecommerceservice.serkancort.dto.mapper.OrderMapper;
import com.ecommerceservice.serkancort.dto.outward.DTOOrder;
import com.ecommerceservice.serkancort.enums.OrderStatus;
import com.ecommerceservice.serkancort.exceptions.ResourceNotFoundException;
import com.ecommerceservice.serkancort.model.*;
import com.ecommerceservice.serkancort.repository.AddressRepository;
import com.ecommerceservice.serkancort.repository.CartRepository;
import com.ecommerceservice.serkancort.repository.CustomerRepository;
import com.ecommerceservice.serkancort.repository.OrderRepository;
import com.ecommerceservice.serkancort.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<DTOOrder> getOrderByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        List<DTOOrder> dtoOrders = orderMapper.orderToDTOList(orders);
        return dtoOrders;
    }

    @Override
    public DTOOrder getOrderByOrderCode(String orderCode) {
        Order response = orderRepository.findByOrderCode(orderCode).orElseThrow(()-> new ResourceNotFoundException("Order code not found for this order code :: " + orderCode));
        DTOOrder dtoOrder = orderMapper.orderToDTO(response);
        return dtoOrder;
    }

    @Transactional
    @Override
    public void deleteOrderByOrderCode(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode).orElseThrow(() -> new ResourceNotFoundException("Order code not found for this order code :: " + orderCode));
        orderRepository.delete(order);
    }

    @Transactional
    public DTOOrder orderPlace( Long customerId ,DTOOrderIU dtoOrderIU) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + dtoOrderIU.getCustomerId()));
        Address address = addressRepository.findById(dtoOrderIU.getAddressId()).orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + dtoOrderIU.getAddressId()));
        Cart cart = cartRepository.findCartByCustomerId(dtoOrderIU.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Cart not found for this customer id :: " + dtoOrderIU.getCustomerId()));
        List<ProductInCart> productsInCart = cart.getProducts();
        List<ProductInOrder>productInOrder = orderMapper.listToOrderPlace(productsInCart);
        AddressInOrder shippingAddress = new AddressInOrder();
        shippingAddress.setAddress(address);

        Order order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderCode(UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase());
        order.setCustomer(customer);
        shippingAddress.setOrder(order);
        order.setShippingAddress(shippingAddress);
        productInOrder.forEach(product -> product.setOrder(order));
        order.setProducts(productInOrder);


        cart.orderPlace();
        cart.getProducts().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
        Order response = orderRepository.save(order);
        return orderMapper.orderToDTO(response);
    }

    public DTOOrder updateOrderAddress(Long orderId , DTOOrderIU dtoOrderIU){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for this order id :: " + orderId));
        Address address = addressRepository.findById(dtoOrderIU.getAddressId()).orElseThrow(()-> new ResourceNotFoundException("Address not found for this id :: " + dtoOrderIU.getAddressId()));
        AddressInOrder shippingAddress = new AddressInOrder();
        shippingAddress.setAddress(address);
        shippingAddress.setOrder(order);
        order.setShippingAddress(shippingAddress);
        Order response = orderRepository.save(order);
        return orderMapper.orderToDTO(response);
    }

    public DTOOrder updateOrderStatus(Long orderId , DTOOrderIU dtoOrderIU){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for this order id :: " + orderId));
        if (dtoOrderIU.getStatus()==null){
            throw new ResourceNotFoundException("Status not found for this order id :: " + orderId);
        }else {
            order.setStatus(dtoOrderIU.getStatus());
        }
        Order response = orderRepository.save(order);
        return orderMapper.orderToDTO(response);
    }

    public  List<DTOOrder> getOrderByCustomerName(String customerName){
        List<Order> orders = orderRepository.findOrderByCustomerName(customerName);
        List<DTOOrder> dtoOrders = orderMapper.orderToDTOList(orders);
        return dtoOrders;
    }

}
