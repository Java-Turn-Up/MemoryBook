package com.example.memorybook.model.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "isbn", columnDefinition = "varchar(13) default")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;
}
