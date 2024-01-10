package com.example.springbootshoppingcart.dao;

import com.example.springbootshoppingcart.dto.OrderQueryParam;
import com.example.springbootshoppingcart.model.Order;
import com.example.springbootshoppingcart.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    List<Order> getOrder(OrderQueryParam orderQueryParam);

    Integer countOrder(OrderQueryParam orderQueryParam);
}
