package com.example.madeprj.mapper;

import com.example.madeprj.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
}
