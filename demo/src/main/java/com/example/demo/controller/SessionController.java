package com.example.demo.controller;

import com.example.demo.model.Session;
import com.example.demo.repository.SessionRepository;
import com.example.demo.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    //это constructor injection (рекомендуемый вариант, не нуждается в аннотациях)
    private SessionService service;
    private SessionRepository sessionRepository;
    public SessionController(SessionService service, SessionRepository sessionRepository){
        this.service = service;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/sessionproof")
    public ResponseEntity<?> sessionproof(@RequestParam String token){
        String checkResult = service.sessionCheckWithToken(token);
        if (!checkResult.equals("false")){
            //return "session exists with given token";
            // отдать 200 код
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            //return "no session exists, please login";
            // отдать 401 код
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getall")
    public List<Session> getSessions(){
        return sessionRepository.getSessions();
    }

}