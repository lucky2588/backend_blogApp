package com.demo.softdreams.global.controller;




import com.demo.softdreams.config.system.rabbitmq.producer.RabbitMQProducer;
import com.demo.softdreams.global.service.EmailService;
import com.demo.softdreams.shared.common.QueueConstance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/public/email")
@Slf4j
public class EmailController {
    @Autowired
    private RabbitMQProducer producer;

    final Logger logger = LoggerFactory.getLogger(EmailController.class);



    @GetMapping("/push")
   public ResponseEntity<String> sendMessage(@RequestBody String message) {
//        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to Rabbit....");
    }














}
