package com.example.basicboardv1.service;

import com.example.basicboardv1.config.security.CustomUserDetails;
import com.example.basicboardv1.mapper.MemberMapper;
import com.example.basicboardv1.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername" + username);

        Member member = memberMapper.selectMemberByUserId(username);
        //username=user id

        if(member == null) {
            throw new UsernameNotFoundException(username + " not found");
        }


        return CustomUserDetails.builder()
                .member(member)
                .build();
    }
}
