package com.example.springbootshoppingcart.dao;

import com.example.springbootshoppingcart.dto.ProductRequest;
import com.example.springbootshoppingcart.model.Product;



public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);
}
