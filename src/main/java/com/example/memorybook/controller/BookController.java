package com.example.memorybook.controller;

import com.example.memorybook.model.entity.Book;
import com.example.memorybook.model.req.RequestBodyBook;
import com.example.memorybook.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

//    [Read] : all
    @GetMapping("/get/all")
    List<Book> getList(){
        return bookService.getBookAll();
    }

//    [Read] : One by id
    @GetMapping("/get/{id}")
    Book getBook(
            @PathVariable String id
    ){
        return bookService.getBookById(id);
    }

//    [Create] : 생성
    @PostMapping("/post")
    ResponseEntity<Void> createBook(
            @RequestBody @Valid RequestBodyBook.BookInfo Info
    ){
        return bookService.postBook(Info);
    }

//    [Update] : Update a book
    @PutMapping(path = "/update/{BOOKID}")
    ResponseEntity<Void> updateBook(
            @PathVariable(name = "BOOKID") String bookIsbn,
            @RequestBody @Valid RequestBodyBook.BookInfo bookInfo
    ){
        return bookService.updateBook(bookIsbn,bookInfo);
    }

//    [Delete] : Delete a book
    @DeleteMapping("/delte/{BOOKID}")
    ResponseEntity<Void> deleteBook(
            @PathVariable(name = "BOOKID") String bookIsbn
    ){
        return bookService.deleteBook(bookIsbn);
    }
}
