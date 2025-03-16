package com.example.basicboardv1.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Builder
public class SignupResponseDTO {
    private boolean success;
}
