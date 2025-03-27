package com.example.webfrontservice.service;

import com.example.webfrontservice.client.AuthClient;
import com.example.webfrontservice.dto.RefreshTokenClientResponseDTO;
import com.example.webfrontservice.dto.RefreshTokenRequestDTO;
import com.example.webfrontservice.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthClient authClient;

    public RefreshTokenClientResponseDTO refreshToken(Cookie[] cookies) {
        String refreshToken = CookieUtil.getCookieValue(cookies, "refreshToken");
        if (refreshToken == null) {
            return null;
        }

        return authClient.refresh(
                RefreshTokenRequestDTO.builder()
                        .refreshToken(refreshToken)
                        .build()
        );
    }
}
