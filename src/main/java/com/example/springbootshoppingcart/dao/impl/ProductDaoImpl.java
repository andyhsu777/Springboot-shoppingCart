package com.example.springbootshoppingcart.dao.impl;

import com.example.springbootshoppingcart.constant.ProductCategory;
import com.example.springbootshoppingcart.dao.ProductDao;
import com.example.springbootshoppingcart.dto.ProdcuctQueryParams;
import com.example.springbootshoppingcart.dto.ProductRequest;
import com.example.springbootshoppingcart.model.Product;
import com.example.springbootshoppingcart.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countProducts(ProdcuctQueryParams prodcuctQueryParams) {
        String sql = "SELECT count(*) from product where 1 =1 ";
        Map<String, Object> map = new HashMap<>();
        //Filter condition
        sql = addFilteringSql(sql,map,prodcuctQueryParams);
        Integer total =  namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }

    @Override
    public List<Product> getProducts(ProdcuctQueryParams prodcuctQueryParams) {
        String sql = "SELECT product_id, product_name, category, image_url," +
                "price, stock, description, created_date, last_modified_date " +
                "FROM product WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql,map,prodcuctQueryParams);

        sql = sql + " ORDER BY " + prodcuctQueryParams.getOrderBy() + " " + prodcuctQueryParams.getSort();
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",prodcuctQueryParams.getLimit());
        map.put("offset",prodcuctQueryParams.getOffset());
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id,product_name, category, image_url, price, stock" +
                ", description, created_date, last_modified_date " +
                "FROM product WHERE product_id = :productId;";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category," +
                "image_url, price, stock, description, created_date," +
                "last_modified_date) VALUES (:productName, :category," +
                ":imageUrl, :price, :stock, :description, :createdDate," +
                ":lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date date = new Date();
        map.put("createdDate", date);
        map.put("lastModifiedDate", date);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName," +
                "category = :category, image_url = :imageUrl, price = :price," +
                "stock = :stock, description = :description, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilteringSql (String sql, Map<String,Object> map, ProdcuctQueryParams prodcuctQueryParams ){
        if (prodcuctQueryParams.getCategory() != null) {
            sql = sql + " AND category = :category";
            map.put("category", prodcuctQueryParams.getCategory().name());
        }

        if (prodcuctQueryParams.getSearch() != null) {
            sql = sql + " AND product_name LIKE :search";
            map.put("search", "%" + prodcuctQueryParams.getSearch() + "%");
        }
        return sql;
    }


}
