package com.example.memorybook.model.req;

import lombok.*;

public class ReqFormatPosting {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicPostingInfo{
        private String bookId;
        private Long creatorId;
        private String title;
        private String content;
        private Long hit;
        private Long like;
        private Long dislike;
    }
}
