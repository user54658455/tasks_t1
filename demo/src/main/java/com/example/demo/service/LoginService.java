package com.example.demo.service;

import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.SessionRepository;
import org.springframework.stereotype.Service;

import static com.example.demo.repository.LoginRepository.generateNewToken;

@Service
public class LoginService {

    //это constructor injection (рекомендуемый вариант, не нуждается в аннотациях)
    private LoginRepository repository;
    private SessionRepository sessionRepository;
    private SessionService sessionService;

    public LoginService(LoginRepository repository, SessionRepository sessionRepository, SessionService sessionService){
        this.repository = repository;
        this.sessionRepository = sessionRepository;
        this.sessionService = sessionService;
    }

    public String loginAndPassCheck(String username, String password){
        if (repository.getCredByLogin(username) != null){
            if (repository.getCredByLogin(username).getPass().equals(password)){
//здесь предыдущая реализация проверяла соответствие логина и токена в хранилище сессий,
//переделал на хранение только токенов чтобы можно было использовать небольшое количество учетных записей
//узкое место здесь в том, что хранилище токенов разрастается с большой скоростью
//(вместо того чтобы изначально использовать огромное количество учетных записей, т.к. логаут и удаление токенов не подразумевались)
//                if (!sessionService.sessionCheckNoToken(username).equals("false")){
//                    return "user already logged in";
//                }
//                else{
//
//                }
                String generatedToken = generateNewToken();
                sessionRepository.addSession(generatedToken);
                return generatedToken;
            }
            else{
                return "false";
            }
        }
        else{
            return "false";
        }
    }

}