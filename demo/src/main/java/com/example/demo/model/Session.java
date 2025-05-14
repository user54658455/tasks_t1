package com.example.demo.model;

public class Session {

    private String userToken;
    public String getUserToken() {
        return userToken;
    }
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    //только для дебага
//    @Override
//    public String toString() {
//        return "Session{" +
//                "userToken='" + userToken + '\'' +
//                '}';
//    }

    //public default constructor чтобы обойти ошибку:
    //"Parameter 0 of constructor in com.example.demo.model.Product required a bean of type 'int' that could not be found."
    public Session(){
    }

    //@Autowired //можно использовать вместо public default constructor чтобы обойти ошибку:
    //"Parameter 0 of constructor in com.example.demo.model.Product required a bean of type 'int' that could not be found."
    public Session(String userToken) {
        this.userToken = userToken;
    }

}