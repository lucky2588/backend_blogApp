package com.demo.softdreams;


import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
//@EnableRedisDocumentRepositories(basePackages = "com.redis.om.documents.*")
public class SoftDreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftDreamsApplication.class, args);
	}



}
