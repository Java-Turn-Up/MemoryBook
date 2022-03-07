package com.example.memorybook.model.req;

import lombok.*;

public class ReqFormatClub {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BasicClubInfo {
        private String club_title;
        private String club_info;
    }
}
