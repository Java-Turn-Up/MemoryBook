package com.example.memorybook.model.entity;

import com.example.memorybook.model.entity.abstract_entity.AbstractTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.security.PrivateKey;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Comment extends AbstractTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cId;

    @Column(name = "comment_author")
    private Long memId;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_like")
    private Long like;

    @Column(name = "comment_dislike")
    private Long dislike;
}

