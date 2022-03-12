package com.example.memorybook.model.entity.relational_Entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "postToComment")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostToComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postToComment_post_id")
    private Long pId;

    @Column(name = "postToComment_comment_id")
    private Long cId;
}
