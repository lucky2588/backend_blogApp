package com.demo.softdreams.config;



import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeanConfig {

    @Bean
    public ModelMapper createInstanceModelMapper(){return new ModelMapper();}
}

