package com.example.springbootshoppingcart.service.impl;

import com.example.springbootshoppingcart.controller.OrderController;
import com.example.springbootshoppingcart.dao.OrderDao;
import com.example.springbootshoppingcart.dao.ProductDao;
import com.example.springbootshoppingcart.dao.UserDao;
import com.example.springbootshoppingcart.dto.BuyItem;
import com.example.springbootshoppingcart.dto.CreateOrderRequest;
import com.example.springbootshoppingcart.dto.OrderQueryParam;
import com.example.springbootshoppingcart.model.Order;
import com.example.springbootshoppingcart.model.OrderItem;
import com.example.springbootshoppingcart.model.Product;
import com.example.springbootshoppingcart.model.User;
import com.example.springbootshoppingcart.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(OrderController.class);

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //check user 是否存在
        User user = userDao.getUserById(userId);
        if(user == null){
            log.warn("this id {} doesn't exist",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //check product is available
            if(product == null){
                log.warn("product {} doesnt' exist",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock() < buyItem.getQuantity()){
                log.warn("product {} stock {} can't be purchase",product.getProductName(),product.getStock());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //decrease stock
            productDao.updateStock(product.getProductId(),product.getStock() - buyItem.getQuantity());



            //calculate amount
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            // BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao.createOrder(userId, totalAmount );

        orderDao.createOrderItems(orderId, orderItemList);
        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    @Override
    public List<Order> getOrder(OrderQueryParam orderQueryParam) {
        List<Order> orderList = orderDao.getOrder(orderQueryParam);

        for(Order order : orderList){
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }

        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParam orderQueryParam) {
        return orderDao.countOrder(orderQueryParam);
    }
}
