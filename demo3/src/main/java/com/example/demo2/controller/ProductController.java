package com.example.demo2.controller;

import java.util.List;

import com.example.demo2.entity.Product;
import com.example.demo2.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService service;

    Logger logger=LoggerFactory.getLogger(ProductController.class);

    //display list of products
    @GetMapping("/")
    public String viewHomepage(Model model){
        return findPaginated(1, "productName","asc",model);


       // model.addAttribute("listProducts", service.getAllProducts());
        //logger.info("getting all the products...");

        //return "index";
    }

    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model){
        //create model attribute to bind form data
        Product product=new Product();
        model.addAttribute("product", product);
        logger.info("adding a new product..");
        return "new_product";

    }
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product,RedirectAttributes redirectattribute){
        //save product to database
        service.saveProduct(product);
        redirectattribute.addFlashAttribute("message","Product added successfully!");
        logger.info("saving a product");
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id ,Model model){
        //get product from the service
        Product product = service.getProductById(id);
        //set product as a model attribute to pre-populate the form
        model.addAttribute("product",product);         
        return "update_product";

    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute("product")Product product,
                                RedirectAttributes redirectAttributes){

    //get product from database by id
    Product existingProduct= service.getProductById(id);
    existingProduct.setId(id);
    existingProduct.setProductName(product.getProductName());
    existingProduct.setBrand(product.getBrand());
    existingProduct.setPrice(product.getPrice());

    //save updated product object 
    service.updateProduct(existingProduct);
    logger.info("product updated..");
    redirectAttributes.addFlashAttribute("message", "product updated successfully");
    return "redirect:/";
                                }
    
    @GetMapping("/DeleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id, Model model ,RedirectAttributes redirectattribute){

        //call delete Product method
        Product product = service.deleteProductById(id);
        model.addAttribute("product", product);
        redirectattribute.addFlashAttribute("message","Product deleted successfully!");  
        logger.info("deleting the product..");
        return "redirect:/";
        //return "product removed !! " + id;        
        

    }
    // /page/1?sortField=name&sortDir=asc
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, 
        @RequestParam("sortField") String sortField,
        @RequestParam("sortDir") String sortDir,
     
     Model model){
        int pageSize = 5;
        Page<Product> page = service.findPaginated(pageNo, pageSize,sortField,sortDir);
        List<Product> listProducts = page.getContent();

        model .addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("FirstPage", 1);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        
        model.addAttribute("listProducts",listProducts);
        return "index";

    }
    }