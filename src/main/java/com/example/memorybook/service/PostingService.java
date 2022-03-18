package com.example.memorybook.service;

import com.example.memorybook.model.entity.Book;
import com.example.memorybook.model.entity.Club;
import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.entity.Posting;
import com.example.memorybook.model.entity.relational_Entity.Posted;
import com.example.memorybook.model.req.ReqFormatPosting;
import com.example.memorybook.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostingService {
    private final PostingRepository postingRepository;
    private final PostedRepository postedRepository;
    private final ClubRepository clubRepository;
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
    @Transactional
    public ResponseEntity<Void> createNewPosting(final ReqFormatPosting.NewPostingBasicInfo req){

        // check
        Book _book = bookRepository.findById(req.getBookId())
                .orElseThrow(() -> new RuntimeException("Book(:" + req.getBookId() + ") is not exist"));
        Member _mem = memberRepository.findByNickName(req.getMemNickname())
                .orElseThrow(() -> new RuntimeException(("Member(:" + req.getMemNickname() + ") is not exist")));
        Club _clb = clubRepository.findById(req.getClubId())
                .orElseThrow(()-> new RuntimeException("Club Name(:" + req.getClubId() + ") is not exist"));

        Posting _posting =  postingRepository.save(
                Posting.builder()
                        .bookId(_book)
                        .creator(_mem)
                        .title(req.getPostingTitle())
                        .content(req.getPostingContent())
                        .like(0L)
                        .dislike(0L)
                        .hit(0L)
                        .build()
        );
        postedRepository.save(Posted.builder()
                .postId(_posting.getPostId())
                .clubId(_clb.getClubId())
                .build()
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // [Delete] : delete a posting
    public ResponseEntity<Void> deletePosting(final Long postingId){
        postingRepository.deleteById(postingId);

        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}
