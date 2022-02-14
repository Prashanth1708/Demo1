package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService service;

    @PostMapping("/product")
    public String aProduct(@RequestBody Product product){
        return service.saveProduct(product);     
    }
    
    @GetMapping("/product")
    public List<Product> findAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/product/{id}")
    public Product findProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @PutMapping("/product")
    public String uProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }
    
    @DeleteMapping("/product/{id}")
    public String delProduct(@PathVariable  int id){
        return service.deleteProduct(id);        
    }
}