package com.example.feignclient.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DataRequestDTO {
    private String name;
    private int value;
}
