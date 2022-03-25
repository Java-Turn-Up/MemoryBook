package com.example.memorybook.controller;

import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.req.ReqFormatMember;
import com.example.memorybook.model.res.ResFormatMember;
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

    //  [Read] : Read
    @GetMapping("/get/{email}")
    ResFormatMember.BasicMemberInfoRes getByEmail (
            @PathVariable("email") String email
    ){
        return memberService.getByEmail(email);
    }

    //    [Read] : all
    @GetMapping("/get/all")
    List<ResFormatMember.BasicMemberInfoRes> getList(){
        return memberService.getMemberAll();
    }

    //    [Read] : One by id
    @GetMapping("/get/byId/{id}")
    ResFormatMember.BasicMemberInfoRes getMember(
            @PathVariable Long id
    ){
        return memberService.getMemberById(id);
    }

    //    [Create] : 생성
    @PostMapping("/post")
    ResponseEntity<Void> createMember(
            @RequestBody @Valid ReqFormatMember.BasicMemberInfo memberInfo
    ){
        return memberService.postMember(memberInfo);
    }

    //    [Update] : Update a member
    @PutMapping(path = "/update/{memberId}")
    ResponseEntity<Void> updateMember(
            @PathVariable(name = "memberId") Long memberId,
            @RequestBody @Valid ReqFormatMember.BasicMemberInfo memberInfo
    ){
        return memberService.updateMember(memberId,memberInfo);
    }

    //    [Delete] : Delete a member
    @DeleteMapping("/delete/{memberId}")
    ResponseEntity<Void> deleteMember(
            @PathVariable(name = "memberId") Long memberId
    ){
        return memberService.deleteMember(memberId);
    }
}
