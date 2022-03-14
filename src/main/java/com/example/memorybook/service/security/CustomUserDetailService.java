package com.example.memorybook.service.security;

import com.example.memorybook.model.entity.Member;
import com.example.memorybook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        log.info("Authentication Started - UserDetailService");
        // 로그인 로직 시작
        // login Id 를 이용하여 DB에서 User 객체를 가져온다.
        Member _obj = memberRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException("Could not found user : " + userEmail));
        // logging
        log.info("Success find member {}", _obj);
        return User.builder()
                .username(_obj.getEmail())
                .password(passwordEncoder.encode(_obj.getPassword()))
                .roles("USER")
                .build();
    }
}
