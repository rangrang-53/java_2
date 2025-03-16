package com.example.tokenlogin.model;

import com.example.tokenlogin.dto.BoardDetailResponseDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Article {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private String filePath;
    private LocalDateTime created;
    private LocalDateTime updated;

    //안 보이게 내부적으로 감싸는 것
    public BoardDetailResponseDTO toBoardDetailResponseDTO() {
        return BoardDetailResponseDTO.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .filePath(filePath)
                .created(created)
                .build();
    }
}
