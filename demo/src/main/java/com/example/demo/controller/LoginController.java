package com.example.demo.controller;

import com.example.demo.model.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class LoginController {

    //не использовалось в нагрузочном скрипте
    @GetMapping("/login")
    public String login(){
        return "This is result of login get request: should be login page with forms, but not usefull for stub";
    }

    //это constructor injection (рекомендуемый вариант, не нуждается в аннотациях)
    private LoginService service;
    private LoginRepository loginRepository;
    public LoginController(LoginService service, LoginRepository loginRepository){
        this.service = service;
        this.loginRepository = loginRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        String checkResult = service.loginAndPassCheck(username, password);
        if (!checkResult.equals("false")){
            //service.saveToken(username, checkResult);
            return "credentials are valid, here your token: \"" + checkResult + "\"";
        }
        else{
            return "Login or password is invalid, please check.";
        }
    }

    @GetMapping("/getall")
    public List<Login> getLogins(){
        return loginRepository.getLogins();
    }

}