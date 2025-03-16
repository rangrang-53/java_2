package com.example.basicboardv1.Controller;

import com.example.basicboardv1.dto.SigninRequestDTO;
import com.example.basicboardv1.dto.SigninResponseDTO;
import com.example.basicboardv1.dto.SignupRequestDTO;
import com.example.basicboardv1.dto.SignupResponseDTO;
import com.example.basicboardv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public SignupResponseDTO join(@RequestBody SignupRequestDTO signupRequestDTO) {
        System.out.println("SignupRequestDTO :: " + signupRequestDTO);
        memberService.signUp(signupRequestDTO.toMember(bCryptPasswordEncoder));
        return SignupResponseDTO.builder()
                                .build();
    }
}
