package com.example.memorybook.service;

import com.example.memorybook.model.entity.BookImage;
import com.example.memorybook.model.httpException.ResponseError;
import com.example.memorybook.model.entity.Book;
import com.example.memorybook.model.req.ReqFormatBook;
import com.example.memorybook.model.res.ResFormatBook;
import com.example.memorybook.model.res.ResFormatClub;
import com.example.memorybook.repository.BookImgRepository;
import com.example.memorybook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookImgRepository bookImgRepository;

    @Value("${file.upload.rootPath}")
    private String uploadRootPath; // String 은 Spring 에서 관리되는 Bean 이 아니기 때문에 private final 로 선언 하면 안됨

    public List<ResFormatBook.BasicBookInfo> getBookAll(){
        return bookRepository.findAll().stream()
                .map(
                        (e) -> (ResFormatBook.BasicBookInfo.builder()
                        .bookTitle(e.getTitle())
                        .bookReview(e.getReview())
                        .bookInfo(e.getInfo())
                        .bookPrice(e.getPrice())
                        .bookAuthor(e.getAuthor())
                        .bookPublisher(e.getPublisher())
                        .build())
                ).collect(Collectors.toList());
    }

    public ResFormatBook.BasicBookInfo getBookById(final String id){
        Book _obj = bookRepository.findById(id).orElseThrow(() -> ResponseError.NotFound.POST_NOT_EXISTS.getResponseException(id.toString()));
        return ResFormatBook.BasicBookInfo.builder()
                .bookPublisher(_obj.getPublisher())
                .bookTitle(_obj.getTitle())
                .bookInfo(_obj.getInfo())
                .bookAuthor(_obj.getAuthor())
                .bookPrice(_obj.getPrice())
                .bookReview(_obj.getReview())
                .build();
    }

    public ResponseEntity<Void> postBook(final ReqFormatBook.BasicBookInfo req){
        final Book _book = Book.builder()
                .isbn(req.getIsbn())
                .title(req.getTitle())
                .author(req.getAuthor())
                .publisher(req.getPublisher())
                .build();
        bookRepository.save(_book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> postListBook(final List<ReqFormatBook.BasicBookInfo> req){
        for(ReqFormatBook.BasicBookInfo _info : req){
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

    public ResponseEntity<Void> updateBook(final String isbn, final ReqFormatBook.BasicBookInfo req){
        final Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> ResponseError.NotFound.POST_NOT_EXISTS.getResponseException(isbn));

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

    public void saveBookImage(final String isbn, final MultipartFile file){
        final Book _book = bookRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book " + isbn + " is not exists."));

        final ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        final String date = String.format("%04d%02d%02d",zonedDateTime.getYear(),zonedDateTime.getMonthValue()+1,zonedDateTime.getDayOfMonth());

        // Tika 를 이용한 확장자 check
        final Tika tika = new Tika();
        String mimeTypeString = null;
        try{
            mimeTypeString = tika.detect(file.getInputStream());
        } catch(Exception e){
            throw new RuntimeException("Invalid file Type");
        }

        // PNG, JPEG 파일 여부 check
        if (!Set.of(
                MediaType.IMAGE_PNG_VALUE,
                MediaType.IMAGE_JPEG_VALUE).contains(mimeTypeString)) {
            throw new RuntimeException("Invalid file Type");
        }

        // automatic file name
        final String extension = mimeTypeString.substring(mimeTypeString.lastIndexOf('/')+1);
        final String fileName = UUID.randomUUID().toString() + "-" + zonedDateTime.getNano() + "." + extension;
        final String folderPath = "/" + date;
        final File folder = new File(uploadRootPath+folderPath);

        if(!folder.exists()){
            folder.mkdir();
        }

        try{
            file.transferTo(new File(uploadRootPath + "/" + date + "/" + fileName));
        }catch(Exception e){
            throw new RuntimeException(e.getCause());
        }

        bookImgRepository.save(BookImage.builder()
                .book(_book)
                .path(uploadRootPath + "/" + date + "/" + fileName)
                .build());
    }
}
