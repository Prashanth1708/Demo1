package com.example.demo2.service;

import java.util.List;
import java.util.Optional;

import com.example.demo2.entity.Product;
import com.example.demo2.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;
    

    @Override
    public  List<Product> getAllProducts(){
        return repository.findAll();

    }

    @Override
    public void saveProduct(Product product) {
        this.repository.save(product);
                
    }

    @Override
    public Product getProductById(long id) {
     Optional<Product> optional =repository.findById(id);
     Product product =null;
     if(optional.isPresent()){
         product = optional.get();
     }else{
         throw new 
         //NoSuchElementException();
         RuntimeException("Product not found for id::" +id);
     }
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        
        return repository.save(product);
    }

    @Override
    public Product deleteProductById(long id) {
            
        Product product =repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id " + id));
        repository.delete(product);
        return product;    
        
    }
        

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
        Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo -1, pageSize,sort);
        return this.repository.findAll(pageable);
    }
                
      
        
        
}