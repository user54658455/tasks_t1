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

    public String sessionCheckWithToken(String token){
        if (sessionRepository.getSessionByToken(token) != null){
            //пробовал сократить размер хранилища сессий в процессе тестов, но удаление происходит не всегда из-за открытой модели нагрузки
            //все равно заглушка съедает место на жетком диске с высокой скоростью
            //в итоге не используется:
            //sessionRepository.removeSession(token);
            return "session exists with given token";
        }
        else{
            return "false";
        }
    }

}