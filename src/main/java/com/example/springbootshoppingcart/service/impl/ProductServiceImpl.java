package com.example.springbootshoppingcart.service.impl;

import com.example.springbootshoppingcart.dao.ProductDao;
import com.example.springbootshoppingcart.dto.ProductRequest;
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

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        Integer productId = productDao.createProduct(productRequest);
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId,productRequest);
    }
}
