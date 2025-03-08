package com.example.tokenlogin.service;

import com.example.tokenlogin.mapper.MemberMapper;
import com.example.tokenlogin.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void signUp(Member member) {
        memberMapper.saved(member);
    }
}
