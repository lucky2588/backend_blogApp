package com.demo.softdreams.global.controller;

import com.demo.softdreams.global.service.AuthService;
import com.demo.softdreams.shared.respone.AuthRes;
import com.demo.softdreams.shared.respone.LoginResquest;
import com.demo.softdreams.shared.respone.RegisterResquest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("login")
    public AuthRes login(@RequestBody LoginResquest loginResquest){
        AuthRes authResponse = authService.login(loginResquest);
        return authResponse;
    };

    @PostMapping("register")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody RegisterResquest resquest){
        authService.registerAccount(resquest);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }




}
