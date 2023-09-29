package com.example.springbootshoppingcart.service;

import com.example.springbootshoppingcart.dto.ProductRequest;
import com.example.springbootshoppingcart.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
