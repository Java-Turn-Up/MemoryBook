package com.example.memorybook.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @SuppressWarnings("unused")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode( "password")).roles("USER").and()
                .withUser("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");
    }
    /*
        HTML 의 폼 태그를 넣어야함
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // spa 등 뭔가를 설정하는거 같음
            .and()
                .csrf().disable() // 세션을 사용하지 않고 JWT 토큰을 활용하여 진행
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 방식이므로 세션설정
            .and()
                .authorizeRequests() // 인증절차에 대한 설정 진행
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/test/**").permitAll()
            .and()
                .formLogin()
//                .loginPage("/login") // 로그인 페이지 url
//                .usernameParameter("username") // 지정 명칭
//                .passwordParameter("password") // 지정 명칭
//                .defaultSuccessUrl("/loging/result") // 로그인 성공 시 이동 페이지
                .permitAll()
            .and()
                .logout();
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 지정 로그아웃 url
//                .logoutSuccessUrl("/index") // log-out 성공 시 이동 페이지
//                .invalidateHttpSession(true) // 세션 초기화
//                .deleteCookies("cookies") // 특정 쿠키를 제거
//            .and()
//                .exceptionHandling()
//                .accessDeniedPage("/error"); // 에러 시 이동할 페이지
//
//                .and()
//                .logout()
//                .logoutUrl("/api/v1/users/sign-out");
    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    @SuppressWarnings("unused")
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
