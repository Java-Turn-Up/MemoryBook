package com.example.memorybook.model.entity;

import com.example.memorybook.model.entity.abstract_entity.AbstractTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends AbstractTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Long memId;

    @Column(name = "mem_firstname")
    private String firstName;

    @Column(name = "mem_lastname")
    private String lastName;

    @Column(name = "mem_email",unique = true)
    private String email;

    @Column(name = "mem_phone")
    private String phone;

    @Column(name = "mem_password")
    private String password;

    @Column(name = "mem_nickname",nullable = false, unique = false)
    private String nickName;
}
