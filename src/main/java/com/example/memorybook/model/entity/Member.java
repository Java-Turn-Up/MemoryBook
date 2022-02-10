package com.example.memorybook.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Long Mem_id;

    @Column(name = "mem_firstName")
    private String FirstName;

    @Column(name = "mem_lastName")
    private String Lastname;

    @Column(name = "mem_email")
    private String Email;

    @Column(name = "mem_phone")
    private String Phone;

    @Column(name = "mem_password")
    private String Password;

    @Column(name = "mem_nickname")
    private String Nickname;
}
