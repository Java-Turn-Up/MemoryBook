package com.example.memorybook.model.req;

import lombok.*;

public class ReqFormatMember {
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicMemberInfo {
        private String FirstName;
        private String Lastname;
        private String Email;
        private String Phone;
        private String Password;
        private String Nickname;
    }
}
