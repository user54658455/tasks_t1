package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//вся "product" часть приложения использовалась в качестве примера-скелета из обучающих уроков и не использовалась при нагрузке
@Service
public class ProductService {

    List<Product> products = new ArrayList<>(
            Arrays.asList(
                    new Product(101, "Iphone", 50000),
                    new Product(102, "Canone Camera", 70000),
                    new Product(103, "Shure Mic", 10000)
            )
    );

    public List<Product> getProducts(){
        return products;
    }

    public Product getProductById(int prodId){
        return products
                .stream()
                .filter(product -> product.getProdId() == prodId)
                .findFirst()
                .orElse(new Product(000, "No Item", 0));
    }

    public void addProduct(Product prod){
        products.add(prod);
    }

}