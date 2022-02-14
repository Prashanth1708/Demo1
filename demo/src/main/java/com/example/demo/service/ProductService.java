package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public String saveProduct(Product product) {
        repository.save(product);
        return  "Product created with id " +product.getId();
    }

    public List<Product> getProducts() {
    return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }


    public String deleteProduct(int id) {
        if(!repository.existsById(id)){
        return "product not found";
        }
        repository.deleteById(id);
        return "product deleted with id " + id;
    }

    public String updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        repository.save(existingProduct);
        return "product updated with id " +product.getId();
    }
}