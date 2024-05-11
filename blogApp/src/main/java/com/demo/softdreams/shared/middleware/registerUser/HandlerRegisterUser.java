package com.demo.softdreams.shared.middleware.registerUser;

import com.demo.softdreams.shared.respone.RegisterResquest;


public abstract class HandlerRegisterUser {


    private HandlerRegisterUser next;


    public static HandlerRegisterUser setNextChain(HandlerRegisterUser next,HandlerRegisterUser... chain) { // set filter chain
        HandlerRegisterUser head = next;
        for (HandlerRegisterUser nextInChain : chain) { // filter chain , if true return next = chain continue
            head.next = nextInChain;
            head = nextInChain;
        }
        return next;
    }

    protected boolean checkNext(){
        return false;
    }




    public abstract HasError checkProperties(RegisterResquest res);
//    protected abstract  HasError hasError();




}
