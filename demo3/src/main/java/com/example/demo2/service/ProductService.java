package com.example.demo2.service;

import java.util.*;

import com.example.demo2.entity.Product;

import org.springframework.data.domain.Page;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    Product getProductById(long id);
    Product updateProduct(Product product);
    Product deleteProductById(long id);
    Page<Product> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);


    
}