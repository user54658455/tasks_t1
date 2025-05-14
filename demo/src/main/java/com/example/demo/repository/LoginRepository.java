package com.example.demo.repository;

import com.example.demo.model.Login;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.*;

@Repository
public class LoginRepository {

    List<Login> logins = new ArrayList<>(
            Arrays.asList(
                    new Login("user1", "pass1"),
                    new Login("user2", "pass2"),
                    new Login("user3", "pass3")
            )
    );

    public List<Login> getLogins(){
        return logins;
    }

    public Login getCredByLogin(String username){
        return logins
                .stream()
                .filter(login -> login.getLogin().equals(username))
                .findFirst()
                .orElse(null);
    }


    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
