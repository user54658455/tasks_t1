package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.Target;

//вся "product" часть приложения использовалась в качестве примера-скелета из обучающих уроков и не использовалась при нагрузке
//@Component  //выставлялось в видеоуроке, но без этого работает
public class Product {

    private int prodId;
    public int getProdId() {
        return prodId;
    }
    public void setProdId(int prodId) {
        this.prodId = prodId;
    }


    private String prodName;
    public String getProdName() {
        return prodName;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }


    private int prodPrice;
    public int getProdPrice() {
        return prodPrice;
    }
    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

//    //только для дебага
//    @Override
//    public String toString() {
//        return "Product{" +
//                "prodId=" + prodId +
//                ", prodName='" + prodName + '\'' +
//                ", prodPrice=" + prodPrice +
//                '}';
//    }


    //public default constructor добавлен чтобы обойти ошибку:
    //"Parameter 0 of constructor in com.example.demo.model.Product required a bean of type 'int' that could not be found."
    public Product(){
    }

    //@Autowired //можно использовать вместо public default constructor чтобы обойти ошибку:
    //"Parameter 0 of constructor in com.example.demo.model.Product required a bean of type 'int' that could not be found."
    public Product(int prodId, String prodName, int prodPrice) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }

}