package com.example.memorybook.model.entity;


import com.example.memorybook.model.entity.abstract_entity.AbstractTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "posting")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posting extends AbstractTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long postId;


    @OneToOne
    @JoinColumn(name = "posting_book",nullable = false)
    private Book bookId;

    @OneToOne
    @JoinColumn(name = "posting_creator",nullable = false)
    private Member creator;

    @Column(name = "posting_title")
    private String title;

    @Column(name = "posting_content")
    private String content;

    @Column(name = "posting_hit" )
    private Long hit;

    @Column(name = "posting_like")
    private Long like;

    @Column(name = "posting_dislike")
    private Long dislike;
}
