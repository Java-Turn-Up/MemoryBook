package com.example.memorybook.model.entity;

import com.example.memorybook.model.entity.abstract_entity.AbstractTimeEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book extends AbstractTimeEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    // info
    @Column(name = "info")
    private String info;
    // review
    @Column(name = "review")
    private String review;
    // price
    @Column(name = "price")
    private Long price;
    // publishedDate
    @Column(name = "publishdate")
    private Date publishdate;
}
