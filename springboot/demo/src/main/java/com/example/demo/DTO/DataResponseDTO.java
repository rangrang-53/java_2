package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
//기본 생성자는 @NoArgsConstructor
public class DataResponseDTO {
    private Long id;
    private String name;
    private int value;
}
