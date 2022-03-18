package com.example.memorybook.configure;

import com.example.memorybook.model.entity.Member;
import com.example.memorybook.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MemberRepository memberRepository;
    /*
     * Spring Security 가 사용자를 인증하는 방법이 담긴 객체
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                    .withUser("user").password(passwordEncoder().encode( "password")).roles("USER")
                .and()
                    .withUser("admin").password(passwordEncoder().encode("admin")).roles("USER", "ADMIN");

        auth.userDetailsService( username -> {
            final Member member = memberRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("There's no user: "+ username));
            return User.builder()
                    .username(member.getEmail())
                    .password(member.getPassword())
                    .roles("USER")
                    .build();
            });
    }
    /*
        각 필터에 대한 세부설정
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

//                .cors() // spa 등 뭔가를 설정하는거 같음
//            .and()
                .csrf().disable() // 세션을 사용하지 않고 JWT 토큰을 활용하여 진행
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 방식이므로 세션설정
//            .and()
                .authorizeRequests() // 인증절차에 대한 설정 진행
                    .antMatchers("/login").permitAll()
                    .antMatchers("/api/**").authenticated()
//                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/test/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
//                .loginPage("/login") // 로그인 페이지 url (Default: springBoot 기본 제공)
//                .loginProcessingUrl("/doLogin")
//                .usernameParameter("username") // 지정 명칭
//                .passwordParameter("password") // 지정 명칭
                .defaultSuccessUrl("/swagger-ui/index.html",false) // 로그인 성공 시 이동 페이지, 원래 가려던 페이지가 있는 경우 alwaysUse 옵션을 false로 설정해야됨
                .permitAll()
            .and()
                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 지정 로그아웃 url
//                .logoutSuccessUrl("/index") // log-out 성공 시 이동 페이지
//                .invalidateHttpSession(true) // 세션 초기화
//                .deleteCookies("cookies") // 특정 쿠키를 제거
//            .and()
//                .exceptionHandling()
//                .accessDeniedPage("/error"); // 에러 시 이동할 페이지
//
                .logoutUrl("/api/v1/users/sign-out");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @SuppressWarnings("unused")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
