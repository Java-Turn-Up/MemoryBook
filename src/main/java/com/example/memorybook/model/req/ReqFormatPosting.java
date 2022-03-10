package com.example.memorybook.model.req;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewPostingBasicInfo{
        @NotBlank
        private String bookId;
        @NotBlank
        private String memNickname;
        @NotBlank
        private String clubTitle;

        private String postingTitle;
        private String postingContent;
    }
}
