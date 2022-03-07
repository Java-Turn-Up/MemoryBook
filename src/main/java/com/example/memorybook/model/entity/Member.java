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
    private Long Mem_id;

    @Column(name = "mem_firstname")
    private String FirstName;

    @Column(name = "mem_lastname")
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
