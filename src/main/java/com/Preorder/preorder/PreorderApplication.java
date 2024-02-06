package com.preorder.preorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ServletComponentScan
@EnableJpaAuditing
public class PreorderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreorderApplication.class, args);
    }

}
