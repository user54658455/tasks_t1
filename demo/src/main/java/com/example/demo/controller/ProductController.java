package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//вся "product" часть приложения использовалась в качестве примера-скелета из обучающих уроков и не использовалась при нагрузке
@RestController
@RequestMapping("/products")
public class ProductController {

//    //@Autowired  //это field injection (не рекомендуемый вариант)
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

    //не использовалось в нагрузочном скрипте
    @GetMapping("/getall")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    //не использовалось в нагрузочном скрипте
    @GetMapping("/{prodId}")
    public Product getProductById(@PathVariable int prodId) {
        return service.getProductById(prodId);
    }

    //не использовалось в нагрузочном скрипте
    @PostMapping("/newproduct")
    public void addProduct(@RequestBody Product prod){
        service.addProduct(prod);
    }

}