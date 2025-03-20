package com.example.orderservice.config;

//yml에 있는 것을 가져오는 역할

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

//polar 산하에 있는 것을 가져와라
@ConfigurationProperties (prefix = "polar")
public record ClientProperties (
        @NotNull
        URI catalogServiceUri
){}
