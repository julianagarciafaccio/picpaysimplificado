package com.picpaySimplificado.picpaysimplificado.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig { // classe de configuraçao
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
