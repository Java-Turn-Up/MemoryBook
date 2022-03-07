package com.example.memorybook.controller;

import com.example.memorybook.model.entity.Posting;
import com.example.memorybook.model.req.ReqFormatPosting;
import com.example.memorybook.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posting")
@RequiredArgsConstructor
public class PostingController {
    private final PostingService postingService;

    @GetMapping("/get/all")
    List<Posting> getAllpost(){
        return postingService.getAllposting();
    }

    @GetMapping("/get/{id}")
    Posting getPost(
            @PathVariable Long id
    ){
        return postingService.getPost(id);
    }

    @PostMapping("/post")
    ResponseEntity<Void> createPosing(
            @RequestBody @Valid ReqFormatPosting.BasicPostingInfo req
    ){
        return postingService.createPosting(req);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deletePosting(
            @PathVariable("id") Long postingId
    ){
        return postingService.deletePosting(postingId);
    }
}
