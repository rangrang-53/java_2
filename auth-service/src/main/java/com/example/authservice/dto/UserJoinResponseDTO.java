package com.example.authservice.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

@Getter
@Builder
public class UserJoinResponseDTO {

    private boolean isSuccess;
}
