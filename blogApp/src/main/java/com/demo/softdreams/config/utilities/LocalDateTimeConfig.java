package com.demo.softdreams.config.utilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LocalDateTimeConfig {

    @Bean
    public LocalDateTime getLocalTime() {
        return LocalDateTime.now();
    }

}
