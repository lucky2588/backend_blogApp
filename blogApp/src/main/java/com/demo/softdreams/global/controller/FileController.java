package com.demo.softdreams.global.controller;


import com.demo.softdreams.core.entites.Image;
import com.demo.softdreams.global.service.EmailService;
import com.demo.softdreams.global.service.FileService;
import com.demo.softdreams.shared.respone.FileRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/files")
@Slf4j
public class FileController {
    private final FileService fileService;
    private final EmailService emailService;
    // 1. Upload file ảnh của User
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file) {
        FileRes fileResponse = fileService.uploadFile(file);
//        String[] adminList = {"thanggago147@gmail.com"};
//        emailService.sendEmailWithFile(adminList,"test","test",file);

        emailService.sendEmailBasic("thanggago147@gmail.com","test","test");
        return new ResponseEntity<>(fileResponse.getUrl(), HttpStatus.CREATED);
    }
    // 2. Xem thông tin file của User
    @GetMapping(value = "{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        Image file = fileService.readFile(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .body(file.getData());
    }



}