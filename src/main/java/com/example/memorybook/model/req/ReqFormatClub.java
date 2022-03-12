package com.example.memorybook.model.req;

import io.swagger.annotations.ApiModel;
import lombok.*;

public class ReqFormatClub {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BasicClubInfo {
        private String clubTitle;
        private String clubInfo;
    }
}
