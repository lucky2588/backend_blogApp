package com.demo.softdreams.shared.middleware.registerUser;

import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.respone.RegisterResquest;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class ValidationPhone extends HandlerRegisterUser{



    @Override
    public HasError checkProperties(RegisterResquest res) {
        HasError hasError = new HasError(false,"");
        Pattern pattern = Pattern.compile(SharedConstance.Regex.REGEX_VIET_NAM_PHONE_NUMBER);
        Matcher matcher = pattern.matcher(res.getMsisdn());
        if(matcher.matches()){
            log.info("Your Phone not vaild ");
             hasError.isError = true;
             hasError.mess = "Your Phone not vaild ";
             return hasError;

        }
        return hasError;
    }
}
