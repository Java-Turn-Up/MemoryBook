package com.example.memorymember.controller;

import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.req.RequestBodyMember;
import com.example.memorybook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //    [Read] : all
    @GetMapping("/get/all")
    List<Member> getList(){
        return memberService.getMemberAll();
    }

    //    [Read] : One by id
    @GetMapping("/get/{id}")
    Member getMember(
            @PathVariable Long id
    ){
        return memberService.getMemberById(id);
    }

    //    [Create] : 생성
    @PostMapping("/post")
    ResponseEntity<Void> createMember(
            @RequestBody @Valid RequestBodyMember.MemberInfo Info
    ){
        return memberService.postMember(Info);
    }

    //    [Update] : Update a member
    @PutMapping(path = "/update/{MEMBERID}")
    ResponseEntity<Void> updateMember(
            @PathVariable(name = "MEMBERID") Long memberId,
            @RequestBody @Valid RequestBodyMember.MemberInfo memberInfo
    ){
        return memberService.updateMember(memberId,memberInfo);
    }

    //    [Delete] : Delete a member
    @DeleteMapping("/delte/{MEMBERID}")
    ResponseEntity<Void> deleteMember(
            @PathVariable(name = "MEMBERID") Long memberId
    ){
        return memberService.deleteMember(memberId);
    }
}
