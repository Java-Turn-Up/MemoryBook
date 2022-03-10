package com.example.memorybook.model.entity.relational_Entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "posted")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Posted {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long id;

    @Column(name = "posted_club_id")
    private Long clubId;

    @Column(name = "posted_posting_id")
    private Long postId;
}
