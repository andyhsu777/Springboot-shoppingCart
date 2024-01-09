package com.example.springbootshoppingcart.service;

import com.example.springbootshoppingcart.dto.CreateOrderRequest;
import com.example.springbootshoppingcart.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    Order getOrderById(Integer orderId);
}
