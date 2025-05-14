package com.example.demo.controller;

import com.example.demo.service.DoubleStringService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doublestring")
public class DoubleStringController {

    //это costructor injection
    private DoubleStringService stringService;
    public DoubleStringController (DoubleStringService stringService){
        this.stringService = stringService;
    }

    //в нагрузке использовалась строка "This is a string." для передачи и удвоения
    @PostMapping("/poststring")
    public String poststring(@RequestBody String string){
        return stringService.doubleString(string);
    }

}