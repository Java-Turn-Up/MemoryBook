package com.example.memorybook.model.req;

import lombok.*;

import java.time.LocalDateTime;

public class RequestBodyBook {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookInfo{
        private String isbn;
        private String title;
        private String author;
        private String publisher;
        private String info;
        private String review;
        private Long price;
        private LocalDateTime publishDate;
    }
}
