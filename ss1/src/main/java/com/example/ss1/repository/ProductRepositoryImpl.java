package com.example.ss1.repository;

import com.example.ss1.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        Product product = entityManager.find(Product.class, productId);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
