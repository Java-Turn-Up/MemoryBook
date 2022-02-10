package com.example.memorybook.service;

import com.example.memorybook.model.httpException.ResponseError;
import com.example.memorybook.model.entity.Book;
import com.example.memorybook.model.req.RequestBodyBook;
import com.example.memorybook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getBookAll(){
        if(bookRepository.findAll().isEmpty()) {
            throw new RuntimeException("Empty List of books!");
        }else{
            return bookRepository.findAll();
        }
    }

    public Book getBookById(final String id){
        return bookRepository.findById(id)
                .orElseThrow(() -> ResponseError.NotFound.POST_NOT_EXISTS.getResponseException(id.toString()));
    }

    public ResponseEntity<Void> postBook(final RequestBodyBook.BookInfo req){
        final Book _book = Book.builder()
                .isbn(req.getIsbn())
                .title(req.getTitle())
                .author(req.getAuthor())
                .publisher(req.getPublisher())
                .build();
        bookRepository.save(_book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> postListBook(final List<RequestBodyBook.BookInfo> req){
        for(RequestBodyBook.BookInfo _info : req){
            final Book _Book = Book.builder()
                    .isbn(_info.getIsbn())
                    .title(_info.getTitle())
                    .author(_info.getAuthor())
                    .publisher(_info.getAuthor())
                    .build();
            bookRepository.save(_Book);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> updateBook(final String isbn, final RequestBodyBook.BookInfo req){
        final Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book "+ isbn + " is not exist"));

        // update
        if(req.getTitle() != null){
            book.setTitle(req.getTitle());
        }
        if(req.getPublisher()!=null){
            book.setPublisher(req.getPublisher());
        }
        if(req.getAuthor()!=null){
            book.setAuthor(req.getAuthor());
        }

        bookRepository.save(book);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Void> deleteBook(final String isbn){
        final Book book = bookRepository.findById(isbn)
                .orElseThrow( () -> new RuntimeException("Book " + isbn + " is not exist"));

        bookRepository.deleteById(isbn);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
