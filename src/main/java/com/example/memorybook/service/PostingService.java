package com.example.memorybook.service;

import com.example.memorybook.model.entity.Book;
import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.entity.Posting;
import com.example.memorybook.model.req.ReqFormatPosting;
import com.example.memorybook.repository.BookRepository;
import com.example.memorybook.repository.MemberRepository;
import com.example.memorybook.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostingService {
    private final PostingRepository postingRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    // [GET] : All post
    public List<Posting> getAllposting(){
        return postingRepository.findAll();
    }
    // [GET] : A post
    public Posting getPost(final Long id){
        return postingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("posting " + id + "is not exist"));

    }

    // [POST] : create a posting
    public ResponseEntity<Void> createPosting(final ReqFormatPosting.BasicPostingInfo req){

        Book _book = bookRepository.findById(req.getBookId())
                .orElseThrow(() -> new RuntimeException("Book " + req.getBookId() + " is not exist"));
        Member _mem = memberRepository.findById(req.getCreatorId())
                .orElseThrow(() -> new RuntimeException(("Member " + req.getCreatorId() + " is not exist")));
        postingRepository.save(
                Posting.builder()
                        .Book_id(_book)
                        .Creator(_mem)
                        .Title(req.getTitle())
                        .Content(req.getTitle())
                        .Hit(req.getHit())
                        .Like(req.getLike())
                        .Dislike(req.getDislike())
                        .build()
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // [Delete] : delete a posting
    public ResponseEntity<Void> deletePosting(final Long psotingId){
        postingRepository.deleteById(psotingId);

        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
