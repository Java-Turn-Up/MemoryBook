package com.example.memorybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
@EnableJpaAuditing // JPA Auditing 활성화
public class MemoryBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryBookApplication.class, args);
    }

}
