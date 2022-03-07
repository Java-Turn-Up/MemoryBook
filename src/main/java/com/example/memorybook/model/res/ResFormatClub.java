package com.example.memorybook.model.res;

import lombok.*;

public class ResFormatClub {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResFormatBasicClubInfo {
        private String club_name;
        private String club_info;
    }

}
