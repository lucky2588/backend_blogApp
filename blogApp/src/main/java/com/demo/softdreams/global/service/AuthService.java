package com.demo.softdreams.global.service;

import com.demo.softdreams.shared.respone.AuthRes;
import com.demo.softdreams.shared.respone.LoginResquest;
import com.demo.softdreams.shared.respone.RegisterResquest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

public interface AuthService {

    public AuthRes login(LoginResquest resquestLogin) throws AuthenticationException;


    // Đăng kí tài khoản => todo : check tài khoản với email , tạo user và gửi về mã token để xác nhận
    public ResponseEntity<?> registerAccount(RegisterResquest resquest);

}
