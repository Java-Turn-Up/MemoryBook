package com.example.memorybook.model.res;

import io.swagger.annotations.ApiModel;
import lombok.*;

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

}
