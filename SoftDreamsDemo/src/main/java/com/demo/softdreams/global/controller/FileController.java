package com.demo.softdreams.global.controller;


import com.demo.softdreams.core.entites.Image;
import com.demo.softdreams.global.service.FileService;
import com.demo.softdreams.shared.res.FileRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/files")
@Slf4j
public class FileController {
    private final FileService fileService;
    // 1. Upload file ảnh của User
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file) {
        FileRes fileResponse = fileService.uploadFile(file);
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