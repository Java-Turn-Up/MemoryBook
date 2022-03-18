package com.example.memorybook.model.req;

import com.example.memorybook.model.constant.signConstant;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ReqFormatUser {
    @Getter
    public static class signIn {
        @NotBlank
        @Pattern(regexp = signConstant.EMAIL_REGEX)
        private String email;
        @NotBlank
        @Pattern(regexp = signConstant.PASSWORD_REGEX)
        private String password;
    }

    @Getter
    public static class signUp {
        @NotBlank
        @Pattern(regexp = signConstant.EMAIL_REGEX)
        private String email;
        @NotBlank
        @Pattern(regexp = signConstant.PASSWORD_REGEX)
        private String password;
        @NotBlank
        @Pattern(regexp = signConstant.NAME_REGEX)
        private String firstName;
        @NotBlank
        @Pattern(regexp = signConstant.NAME_REGEX)
        private String lastName;
        @NotBlank
        @Pattern(regexp = signConstant.PHONE_REGEX)
        private String phone;
        @NotBlank
        @Pattern(regexp = signConstant.NAME_REGEX)
        private String nickName;
    }
}
