package com.example.esssentials.config;

import com.example.esssentials.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoggingFilter> filterRegistrationBean() {
        FilterRegistrationBean<LoggingFilter> filterRegistration = new FilterRegistrationBean<>();

        filterRegistration.setFilter(new LoggingFilter());
        filterRegistration.addUrlPatterns("/api/*");
        //패턴 지정
        filterRegistration.setOrder(1);
        //순서 지정

        return filterRegistration;
    }
}
