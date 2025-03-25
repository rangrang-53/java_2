package com.example.authservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RefreshTokenRequestDTO {
    private String refreshToken;
}
