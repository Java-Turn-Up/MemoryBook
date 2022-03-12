package com.example.memorybook.controller;

import com.example.memorybook.model.entity.Book;
import com.example.memorybook.model.req.ReqFormatBook;
import com.example.memorybook.model.res.ResFormatBook;
import com.example.memorybook.service.BookService;
import com.example.memorybook.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final PostingService postingService;
//    [Read] : all
    @GetMapping("/get/all")
    List<ResFormatBook.BasicBookInfo> getList(){
        return bookService.getBookAll();
    }

//    [Read] : One by id
    @GetMapping("/get/{id}")
    ResFormatBook.BasicBookInfo getBook(
            @PathVariable String id
    ){
        return bookService.getBookById(id);
    }

//    [Create] : 생성
    @PostMapping("/post")
    ResponseEntity<Void> createBook(
            @RequestBody @Valid ReqFormatBook.BasicBookInfo Info
    ){
        return bookService.postBook(Info);
    }

//    [Update] : Update a book
    @PutMapping(path = "/update/{BOOKID}")
    ResponseEntity<Void> updateBook(
            @PathVariable(name = "BOOKID") String bookIsbn,
            @RequestBody @Valid ReqFormatBook.BasicBookInfo bookInfo
    ){
        return bookService.updateBook(bookIsbn,bookInfo);
    }

//    [Delete] : Delete a book
    @DeleteMapping("/delete/{BOOKID}")
    ResponseEntity<Void> deleteBook(
            @PathVariable(name = "BOOKID") String bookIsbn
    ){
        return bookService.deleteBook(bookIsbn);
    }

//    [post]:  Upload img
    @PostMapping("/post/{id}/img")
    ResponseEntity<Void> postImg(
            @PathVariable(name = "id") String isbn,
            @RequestParam("file") MultipartFile file
    ){
        bookService.saveBookImage(isbn,file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
