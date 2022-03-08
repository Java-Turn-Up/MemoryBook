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
}
