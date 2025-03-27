package com.example.webfrontservice.controller;

import com.example.webfrontservice.dto.RefreshTokenClientResponseDTO;
import com.example.webfrontservice.dto.RefreshTokenResponseDTO;
import com.example.webfrontservice.service.TokenService;
import com.example.webfrontservice.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/refresh-token")
    ResponseEntity<?> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        RefreshTokenClientResponseDTO refreshTokenClientResponseDTO = tokenService.refreshToken(request.getCookies());

        if(refreshTokenClientResponseDTO == null || refreshTokenClientResponseDTO.getStatus() != 1){
            return ResponseEntity.status(UNAUTHORIZED)
                    .body("Refresh token failed");
        }

        CookieUtil.addCookie(response, "refreshToken", refreshTokenClientResponseDTO.getRefreshToken(), 7*24*60*60);

        return ResponseEntity.ok(
                RefreshTokenResponseDTO.builder()
                        .status(refreshTokenClientResponseDTO.getStatus())
                        .accessToken(refreshTokenClientResponseDTO.getAccessToken())
                        .build()
        );
    }
}
