package com.example.memorybook.model.res;

import com.example.memorybook.model.entity.Book;
import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.entity.Posting;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

public class ResFormatClub {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResFormatBasicClubInfo {
        private String clubName;
        private String clubInfo;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicClubMemberInfo{
        private String memFirstName;
        private String memLastName;
        private String memEmail;
        private String memPhone;
        private String memNickName;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicClubPostingInfo {
        private Book postingBook;
        private Member postingCreator;
        private String postingTitle;
        private String postingContent;
        private Long hit;
        private Long like;
        private Long dislike;
        private LocalDateTime createdat;
        private LocalDateTime updatedat;
    }
}
