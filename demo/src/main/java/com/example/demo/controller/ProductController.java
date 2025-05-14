package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

//    //@Autowired  //это field injection (НЕ рекомендуемый вариант, private можно не указывать)
//    private ProductService service;

//    //это setter injection (допустимый вариант)
//    private ProductService service;
//    @Autowired
//    public void setService(ProductService service){
//        this.service = service;
//    }

    //это constructor injection (рекомендуемый вариант, не нуждается в аннотациях)
    private ProductService service;
    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping("/getall")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/{prodId}")
    public Product getProductById(@PathVariable int prodId) {
        return service.getProductById(prodId);
    }

    @PostMapping("/newproduct")
    public void addProduct(@RequestBody Product prod){
        service.addProduct(prod);
    }

}