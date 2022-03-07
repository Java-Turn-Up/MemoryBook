package com.example.memorybook.model.req;

import io.swagger.annotations.ApiModel;
import lombok.*;

public class ReqFormatMember {
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicMemberInfo {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String password;
        private String nickname;
    }
}
