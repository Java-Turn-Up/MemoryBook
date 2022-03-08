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
    private Long Post_id;


    @OneToOne
    @JoinColumn(name = "posting_book",nullable = false)
    private Book Book_id;

    @OneToOne
    @JoinColumn(name = "posting_creator",nullable = false)
    private Member Creator;

    @Column(name = "posting_title")
    private String Title;

    @Column(name = "posting_content")
    private String Content;

    @Column(name = "posting_hit" )
    private Long Hit;

    @Column(name = "posting_like")
    private Long Like;

    @Column(name = "posting_dislike")
    private Long Dislike;
}
