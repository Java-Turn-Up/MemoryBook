package com.example.memorybook.service;

import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.req.RequestBodyMember;
import com.example.memorybook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

//    [Read] : Read All member in DB
    public List<Member> getMemberAll() {
        return memberRepository.findAll();
    }

//    [Read]: Read a member
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member " + id + " doesn't exist"));
    }

//    [Update]: Update a member
    public ResponseEntity<Void> updateMember(Long memberId, RequestBodyMember.MemberInfo memberInfo) {
        Member _mem = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member " + memberId + " doesn't exist"));

        if(memberInfo.getFirstName()!=null) {
            _mem.setFirstName(memberInfo.getFirstName());
        }
        if(memberInfo.getLastname()!=null) {
            _mem.setLastname(memberInfo.getLastname());
        }
        if(memberInfo.getEmail()!=null) {
            _mem.setEmail(memberInfo.getEmail());
        }
        if(memberInfo.getPhone()!=null) {
            _mem.setPhone(memberInfo.getPhone());
        }
        if(memberInfo.getPassword()!=null) {
            _mem.setPassword(memberInfo.getPassword());
        }
        if(memberInfo.getNickname()!=null) {
            _mem.setNickname(memberInfo.getNickname());
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    [Create]: Create a new member
    public ResponseEntity<Void> postMember(RequestBodyMember.MemberInfo info) {
        Member _mem = Member.builder()
                .FirstName(info.getFirstName())
                .Lastname(info.getLastname())
                .Email(info.getEmail())
                .Phone(info.getPhone())
                .Password(info.getPassword())
                .Nickname(info.getNickname())
                .build();

        memberRepository.save(_mem);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    [Delete]: Delete a member
    public ResponseEntity<Void> deleteMember(Long memberId) {
        Member _mem = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member " + memberId + " doesn't exist"));

        memberRepository.deleteById(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}