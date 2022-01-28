package com.example.memorybook.model.req;

import lombok.*;

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
    }
}
