package com.example.tokenlogin.config.jwt;

import com.example.tokenlogin.model.Member;
import com.example.tokenlogin.type.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    TokenProvider tokenProvider;

    @Test
    void 토큰_생성_테스트() {
        Member member = Member.builder()
                .id(0L)
                .userId("test")
                .password("123456")
                .userName("test")
                .role(Role.ROLE_USER)
                .build();

        Duration duration = Duration.ofHours(1);
        String token = tokenProvider.generateToken(member, duration);
        System.out.println(token);
        assertNotNull(token);
    }

}