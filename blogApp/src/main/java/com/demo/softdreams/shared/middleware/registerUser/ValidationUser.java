package com.demo.softdreams.shared.middleware.registerUser;

import com.demo.softdreams.shared.respone.RegisterResquest;
import com.demo.softdreams.core.respository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidationUser extends HandlerRegisterUser {
    private final UserRepository dataSource;


    public ValidationUser(UserRepository dataSource) {
        this.dataSource = dataSource;
    }

    @Override // check email is exists
    public HasError checkProperties(RegisterResquest res) {
        boolean isCheck = false;
        HasError error = new HasError(isCheck,"");
        if(dataSource.findByEmail(res.getEmail()).isPresent()){

            isCheck = true;
            return new HasError(isCheck, "Email Exits");
        }
        if(dataSource.findByUsername(res.getUsername()).isPresent()){
            isCheck = true;
            return new HasError(isCheck, "Username Exits");
        }
        if(dataSource.findByMsisdn(res.getMsisdn()).isPresent()){
            isCheck = true;
            return new HasError(isCheck, "phoneNumber Exits");
        }
        return error;
    }



}
