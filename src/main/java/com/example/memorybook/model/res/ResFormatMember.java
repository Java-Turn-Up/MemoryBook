package com.example.memorybook.model.res;

import com.example.memorybook.model.req.ReqFormatMember;
import lombok.*;

import java.time.LocalDateTime;

public class ResFormatMember {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasicMemberInfoRes{
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String nickName;
        private LocalDateTime created;
        private LocalDateTime updated;
    }
}
