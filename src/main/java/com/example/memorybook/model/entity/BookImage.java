package com.example.memorybook.model.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "book_image")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookImage extends AbstractTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn")
    private Book book;

    @Column(name = "path")
    private String path;
}
