package com.example.authservice.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
public class UserLoginRequestDTO {
    private String userId;
    private String password;
}
