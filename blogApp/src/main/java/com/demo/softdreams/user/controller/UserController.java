package com.demo.softdreams.user.controller;
import com.demo.softdreams.core.dto.UserInfo;
import com.demo.softdreams.shared.exception.BadResquestException;
import com.demo.softdreams.shared.res.ChangeInfoUser;
import com.demo.softdreams.shared.res.ChangePassword;
import com.demo.softdreams.shared.res.CustomApiResponse;
import com.demo.softdreams.user.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class UserController {
    @Autowired
    private UserService service;
    // get All
    @PostMapping("changeInfo")
    public UserInfo changeInfo(@RequestBody ChangeInfoUser res){
        return service.changeInfoUser(res);
    }
    @GetMapping("{id}")
    public UserInfo findDetail(@PathVariable Long id){
        return service.findUserById(id);
    }

    @PostMapping("changePassword")
    public UserInfo changePassword(@RequestBody ChangePassword res) throws BadResquestException {
        return service.changePassword(res);
    }






}


