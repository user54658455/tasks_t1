package com.example.demo.service;

import com.example.demo.repository.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    //это constructor injection (рекомендуемый вариант, не нуждается в аннотациях)
    private SessionRepository sessionRepository;
    public SessionService(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

//    public String loginAndPassCheck(String username, String password){
//        //System.out.println(getCredByLogin(username).getPass());
//        if (repository.getCredByLogin(username).getPass().equals(password)){
//            return generateNewToken();
//        }
//        else{
//            return "false";
//        }
//    }

    public String sessionCheckWithToken(String token){
        if (sessionRepository.getSessionByToken(token) != null){
//            if (sessionRepository.getSessionByToken(token).getUserToken().equals(token)){
//                return "session exists with given token";
//            }
//            else{
//                return "false";
//            }
            //sessionRepository.removeSession(token);
            return "session exists with given token";
        }
        else{
            return "false";
        }
    }

//    public String sessionCheckNoToken(String username){
//        if (sessionRepository.getSessionByToken(username) != null){
//            if (sessionRepository.getSessionByToken(username).getUsername().equals(username)){
//                return "session exists, but token not checked";
//            }
//            else{
//                return "false";
//            }
//        }
//        else{
//            return "false";
//        }
//    }



}
