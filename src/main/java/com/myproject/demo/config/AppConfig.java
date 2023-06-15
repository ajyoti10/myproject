package com.myproject.demo.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
   
   @Bean
   public RestTemplate restTemplate(RestTemplateBuilder builder) {
      Duration connectionTimeoutDuration = Duration.ofMillis(10000);
      Duration readTimeoutDuration = Duration.ofMillis(10000);
      builder.setConnectTimeout(connectionTimeoutDuration);
      builder.setReadTimeout(readTimeoutDuration);
      return builder.build();
   }

}
