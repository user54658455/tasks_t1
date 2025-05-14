package com.example.demo.model;

public class Session {

//    private String username;
//    public String getUsername() {
//        return username;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }


    private String userToken;
    public String getUserToken() {
        return userToken;
    }
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "Session{" +
                "userToken='" + userToken + '\'' +
                '}';
    }


    //public default constructor чтобы обойти ошибку
    //"Parameter 0 of constructor in com.example.demo.model.Product required a bean of type 'int' that could not be found."
    //но почему так?
    public Session(){
    }

    //@Autowired //можно использовать вместо public default constructor чтобы обойти ошибку
    //"Parameter 0 of constructor in com.example.demo.model.Product required a bean of type 'int' that could not be found."
    //но как правильно?
    public Session(String userToken) {
        this.userToken = userToken;
    }

}
