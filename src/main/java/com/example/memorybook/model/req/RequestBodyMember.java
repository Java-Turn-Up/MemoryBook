package com.example.memorybook.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

public class RequestBodyMember {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberInfo{
        private String FirstName;
        private String Lastname;
        private String Email;
        private String Phone;
        private String Password;
        private String Nickname;
    }
}
