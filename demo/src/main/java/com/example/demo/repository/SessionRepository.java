package com.example.demo.repository;

import com.example.demo.model.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SessionRepository {

    List<Session> sessions = new ArrayList<>(
            Arrays.asList(
                    //new Session("nousers", "empty")
            )
    );

    public List<Session> getSessions(){
        return sessions;
    }

    public Session getSessionByToken(String token){
        return sessions
                .stream()
                .filter(product -> product.getUserToken().equals(token))
                .findFirst()
                .orElse(null);
    }

    public void addSession(String generatedToken){
        sessions.add(new Session(generatedToken));
        //место на жестком диске съедал этот вывод в консоль
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + sessions);
    }

    //пробовал сократить размер хранилища сессий в процессе тестов, но удаление происходит не всегда из-за открытой модели нагрузки
    //все равно заглушка съедает место на жестком диске с высокой скоростью
    //в итоге не используется
    public void removeSession(String generatedToken){
        Session sxdrg = getSessionByToken(generatedToken);
        sessions.remove(sxdrg);
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! deleted" + sessions);
    }

}