package com.example.catalogservice.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;

@Builder
//record = DTO 같은 느낌
public record Book(
        @Id
        Long id,
        @NotBlank(message = "The Book ISBN must be defined.")
                @Pattern(
                        regexp = "^([0-9]{10}|[0-9]{13})$",
                        message = "THE ISBN format must be valid"
                )
        String isbn,
        @NotBlank(message = "The Book author must be defined.")
        String author,
        @NotBlank(message = "The Book title must be defined.")
        String title,
        @NotNull(message = "The Book price must be defined.")
                @Positive(message = "The Book prise must be greater than zero")
        Double price,
        @Column("create_at")
        @CreatedDate
        Instant createAt,
        @Column("last_modified_at")
        @LastModifiedDate
        Instant lastModifiedAt,
        //동시성 제어
        @Version
        int version
) {
}
