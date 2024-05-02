package com.demo.softdreams.shared.middleware.registerUser;

import com.demo.softdreams.shared.middleware.Middleware;
import com.demo.softdreams.shared.res.RegisterResquest;
import com.demo.softdreams.shared.respository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class HandlerRegisterUser {


    private HandlerRegisterUser next;


    public static HandlerRegisterUser setNextChain(HandlerRegisterUser next) {
//        HandlerRegisterUser head = first;
//        for (HandlerRegisterUser nextInChain : chain) { // create and run all class extend --> doFilter()
//            head.next = nextInChain;
//            head = nextInChain;
//        }
        return next;
    }

    protected boolean checkNext(){
        return false;
    }




    public abstract HasError checkProperties(RegisterResquest res);
//    protected abstract  HasError hasError();




}
