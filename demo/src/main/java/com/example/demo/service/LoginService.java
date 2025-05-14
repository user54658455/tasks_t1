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

//    //это constructor injection (рекомендуемый вариант, не нуждается в аннотациях)
//    private LoginService service;
//    public LoginController(LoginService service){
//        this.service = service;
//    }



    public String loginAndPassCheck(String username, String password){
        if (repository.getCredByLogin(username) != null){
            if (repository.getCredByLogin(username).getPass().equals(password)){
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

//    public void saveToken(String username, String generatedToken) {
//        sessionRepository.addSession(username, generatedToken);
//    }
}