package com.demo.softdreams.global.service.Impl;


import com.demo.softdreams.global.service.EmailService;
import com.demo.softdreams.shared.common.QueueConstance;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.middleware.registerUser.HandlerRegisterUser;
import com.demo.softdreams.shared.respone.ExportData;
import com.demo.softdreams.shared.respone.JsonHttpRes;
import com.demo.softdreams.shared.respone.JsonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EmailServiceIpml implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceIpml.class);
    @Autowired
    private  RestTemplate restTemplate;
//    @Value("${spring.mail.username}")
//    private String sendEmailUrl;

    @Autowired
    private  JavaMailSender javaMailSender;

    //Docs :  https://mailtrap.io/blog/spring-send-email/





    @Override
    public void sendEmailBasic(String email, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage(); // tạo ra đối tưởng Mail đơn giản
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }







    public void exportData(ExportData data){
          logger.info("get MQ in " +data);
    }

@Override
    public void sendEmailWithFile(String[] to, String subject, String text, MultipartFile file) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("to", String.join(",", to));
            body.add("subject", subject);
            body.add("text", text);
            if (file != null) {
                body.add("file", new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                });
            }

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            JsonHttpRes res = restTemplate.postForObject("", requestEntity, JsonHttpRes.class);
            logger.info("Response from sendEmail function: {}", res);
        } catch (Exception e) {
            logger.warn("Error at sendEmail: {}", e.getMessage());
        }
    }




}
