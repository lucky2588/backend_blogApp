//package com.demo.softdreams.config.system.rabbitmq;
//
//import com.itextpdf.text.log.LoggerFactory;
//import lombok.RequiredArgsConstructor;
//import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.AmqpRejectAndDontRequeueException;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
//import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
//
//@EnableRabbit
//@RequiredArgsConstructor
//public   class MyCustomErrorHandler implements RabbitListenerErrorHandler {
//
//    private final Logger logger = (Logger) LoggerFactory.getLogger(MyCustomErrorHandler.class);
//
//
//    public void handleError(Throwable t, Message message) {
//        logger.error("Error processing message: {}", message, t);
//
//        // Your custom error handling logic here:
//
//        // Example 1: Retry up to 3 times (assuming message has a header for retries)
//        Integer retryCount = message.getMessageProperties().getHeader("retryCount");
//        if (retryCount == null || retryCount < 3) {
//            message.getMessageProperties().setHeader("retryCount", retryCount != null ? retryCount + 1 : 1);
//            throw new AmqpRejectAndDontRequeueException("Retrying message");
//        }
//
//        // Example 2: Send to Dead Letter Queue (assuming DLX is configured)
//        // ... (code to send message to DLX)
//    }
//
//    @Override
//    public Object handleError(Message message, org.springframework.messaging.Message<?> message1, ListenerExecutionFailedException e) throws Exception {
//        return null;
//    }
//
//}
