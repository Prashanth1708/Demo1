package com.example.demo2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Validation.constraints.NotBlank;

//import org.hibernate.annotations.Type;




@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private long id;

    //@NotBlank(message = "Product Name is mandatory")
    @Column(name="product_name")
    private String productName;

    @Column(name="brand")
    private String brand;


    @Column(name="price" )

    private double price;

    public Product() {
    }   
    
    
    public Product(long id, String productName, String brand, double price) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.price = price;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


    

    
}