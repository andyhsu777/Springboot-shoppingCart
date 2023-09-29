package com.example.springbootshoppingcart.dao;

import com.example.springbootshoppingcart.constant.ProductCategory;
import com.example.springbootshoppingcart.dto.ProductRequest;
import com.example.springbootshoppingcart.model.Product;

import java.util.List;


public interface ProductDao {

    List<Product> getProducts(ProductCategory category,
                              String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
