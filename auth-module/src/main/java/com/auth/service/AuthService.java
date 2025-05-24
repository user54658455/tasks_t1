package com.auth.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private int globalSleepTime = 0;
    public int getGlobalSleepTime() {
        return globalSleepTime;
    }
    public void setGlobalSleepTime(int globalSleepTime) {
        this.globalSleepTime = globalSleepTime;
    }


    public void setGlobalTimeout(int sleeptime){
        setGlobalSleepTime(sleeptime);
    }

    public void useGlobalTimeout(){
        try {
            Thread.sleep(getGlobalSleepTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}