package com.example.demo.model;

public class Login {

    private String login;
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }


    private String pass;
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }


//    //только для дебага
//    @Override
//    public String toString() {
//        return "Login{" +
//                "login='" + login + '\'' +
//                ", pass='" + pass + '\'' +
//                '}';
//    }


    //public default constructor чтобы обойти ошибку
    //"Parameter 0 of constructor in com.example.demo.model.Product required a bean of type 'int' that could not be found."
    //но почему так?
    public Login(){
    }

    //@Autowired //можно использовать вместо public default constructor чтобы обойти ошибку
    //"Parameter 0 of constructor in com.example.demo.model.Product required a bean of type 'int' that could not be found."
    //но как правильно?
    public Login(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }



}
