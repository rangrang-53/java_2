package com.example.basicboardv1.dto;

import com.example.basicboardv1.model.Member;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@ToString
public class SigninRequestDTO {
    private String userId;
    private String password;

}
