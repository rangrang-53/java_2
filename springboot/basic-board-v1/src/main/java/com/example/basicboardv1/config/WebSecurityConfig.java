package com.example.basicboardv1.config;


import com.example.basicboardv1.config.security.CustomAuthenticationFailureHandler;
import com.example.basicboardv1.config.security.CustomAuthenticationSuccessHandler;
import com.example.basicboardv1.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/static/**", "/css/**", "/js/**"
                );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   CustomAuthenticationSuccessHandler successHandler,
                                                   CustomAuthenticationFailureHandler failureHandler) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers(
                                        new AntPathRequestMatcher("/join", "POST"),
                                        new AntPathRequestMatcher("/member/join", "GET"),
                                        new AntPathRequestMatcher("/member/login", "GET")
                                ).permitAll()
                                .anyRequest().authenticated()
                );

        http
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/member/login")
                                .loginProcessingUrl("/login")
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
