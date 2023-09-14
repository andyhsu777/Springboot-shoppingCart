package com.example.springbootshoppingcart.service.impl;

import com.example.springbootshoppingcart.dao.ProductDao;
import com.example.springbootshoppingcart.model.Product;
import com.example.springbootshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);
    }
}
