package com.example.memorybook.service.security;

import com.example.memorybook.model.entity.Member;
import com.example.memorybook.model.httpException.ResponseError;
import com.example.memorybook.model.req.ReqFormatUser;
import com.example.memorybook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        log.info("Authentication Started - UserDetailService");
        // 로그인 로직 시작
        // login Id 를 이용하여 DB 에서 User 객체를 가져온다.
        Member _obj = memberRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException("Could not found user : " + userEmail));
        // logging
        log.info("Success find member {}", _obj);
        return User.builder()
                .username(_obj.getEmail())
                .password(passwordEncoder.encode(_obj.getPassword()))
                .roles("USER")
                .build();
    }
    public Authentication signIn(final ReqFormatUser.signIn req){
        return authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
    }

    public void signUp(final ReqFormatUser.signUp req){
        final Member member = Member.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .phone(req.getPhone())
                .nickName(req.getNickName())
                .build();
        try{
            memberRepository.save(member);
        }catch (DataIntegrityViolationException e){
            Optional.ofNullable(e.getCause())
                    .filter(cause -> cause instanceof ConstraintViolationException)
                    .map(cause -> (ConstraintViolationException) cause)
                    .map(ConstraintViolationException::getSQLException)
                    .map(SQLException::getErrorCode)
                    .filter(code -> code.equals(1062))
                    .ifPresent(code -> {
                        throw ResponseError.BadRequest.ALREADY_EXISTS_EMAIL.getResponseException();
                    });

            throw e;
        }
    }
}
