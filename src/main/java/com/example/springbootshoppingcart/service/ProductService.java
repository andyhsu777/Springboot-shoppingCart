package com.example.springbootshoppingcart.service;

import com.example.springbootshoppingcart.dto.ProductRequest;
import com.example.springbootshoppingcart.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);
}
