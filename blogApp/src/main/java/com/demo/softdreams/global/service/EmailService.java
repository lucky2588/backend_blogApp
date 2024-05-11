package com.demo.softdreams.global.service;

import com.demo.softdreams.shared.respone.ExportData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface EmailService {


//    public void checkEmail(String mess);



void exportData(ExportData data);

void sendEmailWithFile(String[] to, String subject, String text, MultipartFile file);
    void sendEmailBasic(String email, String subject, String content);



}
