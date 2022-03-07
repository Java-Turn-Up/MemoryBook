package com.example.memorybook.model.req;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

public class ReqFormatBook {
    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicBookInfo {
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
