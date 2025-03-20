package com.example.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    WebClient webClient(
            ClientProperties clientProperties,
            WebClient.Builder webClientBuilder
    ) {
        return webClientBuilder
                //toString을 써도 되는 이유 -> NotNull 어노테이션을 붙였기 때문
                //toString보다 String.valueOf가 더 안전
                .baseUrl(String.valueOf(clientProperties.catalogServiceUri()))
                .build();
    }
}
