package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.Product;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    Product findByName(String name); 

    static void deleteProductById(int id) {
    }

    static Optional<User> findProductById(int id) {
        return null;
    }

    Optional<Product> findById(String id);
}