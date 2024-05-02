package com.demo.softdreams.shared.middleware;


import com.demo.softdreams.shared.middleware.registerUser.HasError;

// Declare / Defition Filter doFilter resquest before service handlen ...
public abstract class Middleware {

    // ref : https://www.youtube.com/watch?v=ugkGgSurw10
    // ref : https://gpcoder.com/tag/structuaral-pattern/

    private Middleware next; // return value for chain continue ...


    public Middleware setNextChain(Middleware next) {
        this.next = next;
        return next;
    }





}
