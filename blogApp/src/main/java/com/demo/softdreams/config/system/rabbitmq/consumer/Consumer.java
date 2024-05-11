package com.demo.softdreams.config.system.rabbitmq.consumer;

import aj.org.objectweb.asm.TypeReference;
import com.demo.softdreams.core.exception.BadResquestException;
import com.demo.softdreams.global.service.EmailService;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.respone.ExportData;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class Consumer {
    private final ObjectMapper objectMapper;
    @Autowired
    private  RabbitTemplate rabbitTemplate;
    @Autowired
    private EmailService emailService;








//        ExportData dataExport = (ExportData) data;
//        emailService.exportData(dataExport);

    }







