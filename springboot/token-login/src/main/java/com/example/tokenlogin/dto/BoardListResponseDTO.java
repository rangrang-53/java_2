package com.example.tokenlogin.dto;

import com.example.tokenlogin.model.Article;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardListResponseDTO {
    //모델로 빼는 건 안 좋음 (클라이언트로 노출되면 안됨)
    List<Article> articles;
    boolean last;
}
