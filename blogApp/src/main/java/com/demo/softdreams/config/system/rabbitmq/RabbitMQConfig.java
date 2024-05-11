package com.demo.softdreams.config.system.rabbitmq;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;


@Configuration
@EnableRabbit
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.host}")
    String host;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routingkey}")
    private String routingKey;
    @Autowired
    private  ObjectMapper objectMapper;

    // spring for RabbitMQ queue
    @Bean
    public Queue queue(){
        return new Queue(queue, true, false, true);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(){ // config binding form exchange to Queue
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }


    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter(objectMapper);
    }


    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }



    @Bean
    public Queue errorQueue() {
        return new Queue("error-queue", true);
    }

    @Bean
    public DirectExchange errorExchange() {
        return new DirectExchange("error-exchange");
    }

    @Bean
    public Binding errorBinding() {
        return BindingBuilder.bind(errorQueue()).to(errorExchange()).with("error-routingkey");
    }
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ErrorHandler myErrorHandler, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setErrorHandler(myErrorHandler);
        return factory;
    }
    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy());
    }
    public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

        @Override
        public boolean isFatal(Throwable t) {
            if (t instanceof ListenerExecutionFailedException) {
                ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
            }
            return super.isFatal(t);
        }
    }



//
//    public static final String RPC_QUEUE1 = "queue_1";
//    public static final String RPC_QUEUE2 = "queue_2";
//    public static final String RPC_EXCHANGE = "rpc_exchange";
//    @Value("${spring.rabbitmq.host}")
//    String host;
//
//    @Value("${spring.rabbitmq.username}")
//    String username;
//
//    @Value("${spring.rabbitmq.password}")
//    String password;
//
//    @Value("${spring.rabbitmq.queue}")
//    private String queue;
//
//    @Value("${spring.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${spring.rabbitmq.routingkey}")
//    private String routingKey;
//
//    @Bean
//    Queue msgQueue() {
//        return new Queue(RPC_QUEUE1);
//    }
//
//    @Bean
//    Queue replyQueue() {
//        return new Queue(RPC_QUEUE2);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(RPC_EXCHANGE);
//    }
//
//    @Bean
//    Binding msgBinding() {
//        return BindingBuilder.bind(msgQueue()).to(exchange()).with(RPC_QUEUE1);
//    }
//
//    @Bean
//    Binding replyBinding() {
//        return BindingBuilder.bind(replyQueue()).to(exchange()).with(RPC_QUEUE2);
//    }
//
//
//
//    @Bean
//    Exchange myExchange() {
//        return ExchangeBuilder.directExchange(exchange).durable(true).build();
//    }
//
//    @Bean
//    Queue queue() {
//        return new Queue(queue, true);
//    }
//
//    @Bean
//    Binding binding() {
//        return BindingBuilder
//                .bind(queue())
//                .to(myExchange())
//                .with(routingKey)
//                .noargs();
//    }
//
//    @Bean
//    public Queue errorQueue() {
//        return new Queue("error-queue", true);
//    }
//
//    @Bean
//    public DirectExchange errorExchange() {
//        return new DirectExchange("error-exchange");
//    }
//
//    @Bean
//    public Binding errorBinding() {
//        return BindingBuilder.bind(errorQueue()).to(errorExchange()).with("error-routingkey");
//    }
//
//    @Bean
//    CachingConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
//        cachingConnectionFactory.setUsername(username);
//        cachingConnectionFactory.setPassword(password);
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        return rabbitTemplate;
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter( jsonMessageConverter());
//        factory.setErrorHandler(errorHandler());
//        return factory;
//    }
//
//    @Bean
//    public ErrorHandler errorHandler() {
//        return new ConditionalRejectingErrorHandler(new SoftDreamsApplication.MyFatalExceptionStrategy());
//    }
//




}






