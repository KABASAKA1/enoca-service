package com.ecommerceservice.serkancort.service.imp;

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

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<DTOOrder> getOrderByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .map(orderMapper::orderToDTOList)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found with customer id : "+customerId));
    }

    @Override
    public DTOOrder getOrderByOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode)
                .map(orderMapper::orderToDTO)
                .orElseThrow(()-> new ResourceNotFoundException("Order code not found for this order code :: " + orderCode));
    }

    @Transactional
    @Override
    public void deleteOrderByOrderCode(String orderCode) {
        orderRepository.findByOrderCode(orderCode)
                .ifPresentOrElse(
                        orderRepository::delete,
                        ()-> {throw  new ResourceNotFoundException("Order code not found for this order code :: " + orderCode);}
                );
    }

    @Transactional
    public DTOOrder orderPlace( Long customerId ,DTOOrderIU dtoOrderIU) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId + " for order place."));
        Address address = addressRepository.findById(dtoOrderIU.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + dtoOrderIU.getAddressId() + " for order place."));
        Cart cart = cartRepository.findCartByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for this customer id :: " + customerId + " for order place."));

        if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new ResourceNotFoundException("The cart is empty. Cannot place an order.");
        }

        List<ProductInOrder> productInOrder = orderMapper.listToOrderPlace(cart.getProducts());

        Order order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderCode(UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase());
        order.setCustomer(customer);

        AddressInOrder shippingAddress = new AddressInOrder();
        shippingAddress.setAddress(address);
        shippingAddress.setOrder(order);
        order.setShippingAddress(shippingAddress);

        productInOrder.forEach(product -> product.setOrder(order));
        order.setProducts(productInOrder);

        cartService.orderPlace(cart);

        return orderMapper.orderToDTO(orderRepository.save(order));
    }

    public DTOOrder updateOrderAddress(Long orderId , DTOOrderIU dtoOrderIU){
        Address address = addressRepository.findById(dtoOrderIU.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + dtoOrderIU.getAddressId()));
        return orderRepository.findById(orderId)
                .map(order -> {
                    AddressInOrder shippingAddress = new AddressInOrder();
                    shippingAddress.setAddress(address);
                    shippingAddress.setOrder(order);
                    order.setShippingAddress(shippingAddress);
                    return orderRepository.save(order);
                })
                .map(orderMapper::orderToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this order id :: " + orderId));
    }

    public DTOOrder updateOrderStatus(Long orderId , DTOOrderIU dtoOrderIU){
        if (dtoOrderIU.getStatus() == null) {
            throw new IllegalArgumentException("Status cannot be null for order id: " + orderId);
        }
        return orderRepository.findById(orderId)
                .map(order -> {
                    order.setStatus(dtoOrderIU.getStatus());
                    return orderRepository.save(order);
                })
                .map(orderMapper::orderToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for order id: " + orderId));
    }

    public  List<DTOOrder> getOrderByCustomerName(String customerName){
        return orderRepository.findOrderByCustomerName(customerName)
                .map(orderMapper::orderToDTOList)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found with customer name : "+customerName));
    }

}
