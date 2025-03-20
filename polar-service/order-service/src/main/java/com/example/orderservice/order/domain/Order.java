package com.example.orderservice.order.domain;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
//실제 매핑하는 테이블 명시
@Table("orders")
public record Order(
        @Id Long id,

        String bookIsbn,
        String bookName,
        Double bookPrice,
        Integer quantity,
        OrderStatus status,
        @CreatedDate LocalDateTime createdDate,
        @LastModifiedDate LocalDateTime lastModifiedDate,

        @Version int version
        ) {
}
