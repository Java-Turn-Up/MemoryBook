package com.example.memorybook.model.req;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RequestBodyBook {
    @Getter
    @Builder
    public static class BookInfo{
        private String isbn;
        private String title;
        private String author;
        private String publisher;
    }
}
