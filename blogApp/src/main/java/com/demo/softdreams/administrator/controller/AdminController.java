package com.demo.softdreams.administrator.controller;
import com.demo.softdreams.administrator.dto.blog.ChangeActive;
import com.demo.softdreams.administrator.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/manage/admin/blog")
public class AdminController {
    private final AdminService service;


    @PostMapping(value = "/change-active")
    public void changeActiveBlog(@RequestBody ChangeActive res){
        service.changeActiveBlog(res);
    }






























}
