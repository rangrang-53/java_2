package com.example.authservice.service;

import com.example.authservice.dto.ClaimsResponseDTO;
import com.example.authservice.dto.RefreshTokenResponseDTO;
import com.example.authservice.dto.ValidTokenResponseDTO;
import com.example.authservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
//refresh token 발급 & vaild token 체크
public class TokenService {

    private final TokenProviderService tokenProviderService;

    public RefreshTokenResponseDTO refreshToken(String refreshToken) {
        int result = tokenProviderService.validToken(refreshToken);
        String newAccessToken = null;
        String newRefreshToken = null;

        if (result == 1) {
            User user = tokenProviderService.getTokenDetails(refreshToken);

            newAccessToken = tokenProviderService.generateToken(user, Duration.ofHours(2));
            newRefreshToken = tokenProviderService.generateToken(user, Duration.ofDays(2));
        }

        return RefreshTokenResponseDTO.builder()
                .status(result)
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public ClaimsResponseDTO getAuthentication(String token) {
        return tokenProviderService.getAuthentication(token);
    }

    public ValidTokenResponseDTO validateToken(String token) {
        int result = tokenProviderService.validToken(token);
        return ValidTokenResponseDTO.builder()
                .statusNum(result)
                .build();
    }
}
