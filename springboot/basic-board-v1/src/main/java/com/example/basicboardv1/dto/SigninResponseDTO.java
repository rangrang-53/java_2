package com.example.basicboardv1.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SigninResponseDTO {
    private boolean success;
    private String userId;
    private String userName;
}

