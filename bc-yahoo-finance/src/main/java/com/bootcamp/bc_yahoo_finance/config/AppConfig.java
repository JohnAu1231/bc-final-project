package com.bootcamp.bc_yahoo_finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_yahoo_finance.infra.RedisHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
  
  @Bean
  RestTemplate beanRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  ObjectMapper beanObjectMapper() {
    return new ObjectMapper();
  }

  @Bean
  RedisHelper beanRedisHelper(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    return new RedisHelper(factory, objectMapper);
  } 
}
