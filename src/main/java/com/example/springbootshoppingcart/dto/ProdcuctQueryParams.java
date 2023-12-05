package com.example.springbootshoppingcart.dto;

import com.example.springbootshoppingcart.constant.ProductCategory;

public class ProdcuctQueryParams {
    private ProductCategory category;
    private String search;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
