package com.bootcamp.bc_stock_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Appconfig {
  
  @Bean
  public RestTemplate beanRestTemplate() {
    return new RestTemplate();
  }

}
