package com.example.memorybook.service;

import com.example.memorybook.model.entity.Book;
import com.example.memorybook.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureWebTestClient
public class BookServiceTests {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void getTest(){
        Book _book = Book.builder()
                .author("testing")
                .build();

        System.out.println("This is Testing : "+_book.getCreatedAt());

    }
    @Test
    public void getTest2(){
        System.out.println("Testing isNormal?");

    }
}
