package com.demo.softdreams.administrator.service.impl;

import com.demo.softdreams.administrator.dto.blog.ChangeActive;
import com.demo.softdreams.administrator.service.AdminService;
import com.demo.softdreams.config.system.rabbitmq.producer.RabbitMQProducer;
import com.demo.softdreams.core.exception.NotFoundException;
import com.demo.softdreams.core.respository.BlogRepository;
import com.demo.softdreams.shared.common.SharedConstance;
import com.demo.softdreams.shared.respone.CustomApiResponse;
import com.demo.softdreams.shared.respone.IdReq;
import com.demo.softdreams.shared.service.ExportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMQProducer producer;
    private final ObjectMapper objectMapper;
    @Autowired
    private BlogRepository dataSource;




    @Override
    public void changeActiveBlog(ChangeActive res) {
        String receive;
            try {
                receive = objectMapper.writeValueAsString(res);
            } catch (JsonProcessingException exception ) {
                throw new NotFoundException(" Cant coverstation to data form String !! ");
            }
            log.info(" Messagea before receive "+receive);
            try {
                producer.sendMessage(receive);
            } catch (Exception exception) {
                log.info("Fail in send data in RabbitMQ");
                rabbitTemplate.convertAndSend("error-exchange", "error-routingkey", exception.getMessage());
            }
    }



}
