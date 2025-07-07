package com.example.ss1.repository;

import com.example.ss1.entity.Product;

import java.util.List;

public interface ProductRepository {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long productId);
    List<Product> getAllProducts();
}
