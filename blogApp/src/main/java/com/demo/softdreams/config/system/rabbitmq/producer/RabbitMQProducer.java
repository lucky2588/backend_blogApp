package com.demo.softdreams.config.system.rabbitmq.producer;

import com.demo.softdreams.shared.respone.ExportData;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RabbitMQProducer  {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;
   @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message){
//        LOGGER.info(String.format("Message sent)->%s",message));
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }





}
