package com.mystudy.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dalaoban
 * @create 2020-08-19-10:18
 */
@Configuration
public class SpringMvcConfig {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
