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
    @ApiModel(value = "도서 정보" , description = "도서ID, 제목, 저자, 출판사, 정보, 리뷰, 가격, 출판일")
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
