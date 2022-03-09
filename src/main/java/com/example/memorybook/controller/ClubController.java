package com.example.memorybook.controller;


import com.example.memorybook.model.req.ReqFormatClub;
import com.example.memorybook.model.res.ResFormatClub;
import com.example.memorybook.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/club")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    // [Get] : get a club info
    @GetMapping("/get/{clubName}")
    ResFormatClub.ResFormatBasicClubInfo getClub(
            @PathVariable("clubName") String clubName
    ){
        return clubService.getClubInfo(clubName);
    }

    // [Get] : get all club info
    @GetMapping("/get/all")
    List<ResFormatClub.ResFormatBasicClubInfo> getAllclub(){
        return clubService.getAllClub();
    }

    // [Get] : get all club member
    @GetMapping("/get/member/{clubId}")
    List<ResFormatClub.BasicClubMemberInfo> getMembersInClub(
            @PathVariable("clubId") Long id
    ){
        return clubService.getMembers(id);
    }

    // [Get] : get all postings in club
    @GetMapping("/get/postings/{clubId}")
    List<ResFormatClub.BasicClubPostingInfo> getPostingsInClub(
            @PathVariable("clubId") Long id
    ){
        return clubService.getPostings(id);
    }

    // [Post] : post a club
    @PostMapping("/post")
    ResponseEntity<Void> postClub(
            @RequestBody @Valid ReqFormatClub.BasicClubInfo req
            ){
        return clubService.postClub(req);
    }

    // [Delete] : delete a club
    @DeleteMapping("/delete/{clubName}")
    ResponseEntity<Void> deleteClub(
            @PathVariable("clubName") String reqName
    ){
        return clubService.deleteClub(reqName);
    }
}
