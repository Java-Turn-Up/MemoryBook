package com.example.memorybook.model.res;

import io.swagger.annotations.ApiModel;
import lombok.*;

public class ResFormatBook {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicBookInfo{
        private String bookTitle;
        private String bookAuthor;
        private String bookPublisher;
        private Long bookPrice;
        private String bookInfo;
        private String bookReview;
    }
}
