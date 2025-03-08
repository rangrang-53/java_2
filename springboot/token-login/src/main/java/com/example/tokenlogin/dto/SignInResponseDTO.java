package com.example.tokenlogin.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponseDTO {
    private boolean isLoggined;
    private String userId;
    private String userName;
    private String token; //access token , JS에서 받아서 로컬스토리지에 저장 (브라우저별로 되는 것)
}
