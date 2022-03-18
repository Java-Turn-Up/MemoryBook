package com.example.memorybook.model.constant;

public interface signConstant {
    String EMAIL_REGEX = "^[0-9a-zA-Z]+[-.\\w]*@[0-9a-zA-Z]+[-.\\w]*(\\.[0-9a-zA-Z]+)+$";
    // 대문자 1문자 & 특수문자 1문자 이상을 포함한 비밀번호
    String PASSWORD_REGEX = "^(?=.*\\d+)(?=.*[A-Z]+)(?=.*[a-z]+)(?=.*[!@#$%\\^&*()]+)[A-Za-z\\d!@#$%\\^&*()]{8,20}$";
    String NAME_REGEX = "^(?=.*[A-Za-z가-힣]+)[A-Za-z가-힣\\s]{1,30}$";
    // '-' 을 포함한 11자리 전화번호
    // ex) 01063817654
//    String PHONE_REGEX = " ^[0-9]{3}[-]+[0-9]{4}[-]+[0-9]{4}$";
    String PHONE_REGEX = "^[0-9]{11}$";
}
