package com.demo.softdreams.administrator.controller;

import com.demo.softdreams.core.entites.Blog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/manage/user")
public class ManageUserController {



    @GetMapping
    public String getMss(){
        Blog blog = new Blog();



        return "Say Hello";

    }
}
