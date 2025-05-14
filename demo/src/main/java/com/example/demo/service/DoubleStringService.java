package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class DoubleStringService {

    public String doubleString(String string) {
        string = string + string;
        return string;
    }
}