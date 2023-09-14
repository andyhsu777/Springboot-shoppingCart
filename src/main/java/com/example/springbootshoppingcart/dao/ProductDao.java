package com.example.springbootshoppingcart.dao;

import com.example.springbootshoppingcart.model.Product;



public interface ProductDao {
    Product getProductById(Integer productId);
}
