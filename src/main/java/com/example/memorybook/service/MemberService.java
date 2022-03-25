package com.example.memorybook.service;

import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.req.ReqFormatMember;
import com.example.memorybook.model.res.ResFormatMember;
import com.example.memorybook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

//    [Read] : Read member by email
    public ResFormatMember.BasicMemberInfoRes getByEmail(final String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Member " + email + " doesn't exist"));
        return ResFormatMember.BasicMemberInfoRes.builder()
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .nickName(member.getNickName())
                .created(member.getCreatedat())
                .updated(member.getUpdatedat())
                .build();
    }

//    [Read] : Read All member in DB
    public List<ResFormatMember.BasicMemberInfoRes> getMemberAll() {
        return memberRepository.findAll().stream()
                .map(
                        (e) -> (ResFormatMember.BasicMemberInfoRes.builder()
                                .firstName(e.getFirstName())
                                .lastName(e.getLastName())
                                .email(e.getEmail())
                                .phone(e.getPhone())
                                .nickName(e.getNickName())
                                .created(e.getCreatedat())
                                .updated(e.getUpdatedat())
                                .build())
                ).collect(Collectors.toList());
    }

//    [Read]: Read a member
    public ResFormatMember.BasicMemberInfoRes getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member " + id + " doesn't exist"));
        return ResFormatMember.BasicMemberInfoRes.builder()
                .firstName(member.getFirstName())
                .created(member.getCreatedat())
                .updated(member.getUpdatedat())
                .phone(member.getPhone())
                .email(member.getEmail())
                .lastName(member.getLastName())
                .nickName(member.getNickName())
                .build();
    }

//    [Update]: Update a member
    public ResponseEntity<Void> updateMember(Long memberId, ReqFormatMember.BasicMemberInfo memberInfo) {
        Member _mem = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member " + memberId + " doesn't exist"));

        if(memberInfo.getFirstName()!=null) {
            _mem.setFirstName(memberInfo.getFirstName());
        }
        if(memberInfo.getLastName()!=null) {
            _mem.setLastName(memberInfo.getLastName());
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
            _mem.setNickName(memberInfo.getNickname());
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    [Create]: Create a new member
    public ResponseEntity<Void> postMember(ReqFormatMember.BasicMemberInfo info) {
        Member _mem = Member.builder()
                .firstName(info.getFirstName())
                .lastName(info.getLastName())
                .email(info.getEmail())
                .phone(info.getPhone())
                .password(info.getPassword())
                .nickName(info.getNickname())
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
