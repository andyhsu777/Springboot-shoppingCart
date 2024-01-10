package com.example.springbootshoppingcart.service;

import com.example.springbootshoppingcart.dto.CreateOrderRequest;
import com.example.springbootshoppingcart.dto.OrderQueryParam;
import com.example.springbootshoppingcart.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
    List<Order> getOrder(OrderQueryParam orderQueryParam);
    Integer countOrder(OrderQueryParam orderQueryParam);
}
