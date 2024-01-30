package com.preorder.preorder.Config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    // 메일 설정
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.naver.com");
        mailSender.setUsername(username); // 네이버 아이디
        mailSender.setPassword(password); // 네이버 비밀번호
        mailSender.setPort(587); // TLS 포트 설정

        mailSender.setJavaMailProperties(getMailProperties());
        return mailSender;
    }

    // 메일 속성 설정
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true"); // TLS 활성화
        properties.setProperty("mail.debug", "true"); // 디버그 정보 출력 활성화
        properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com");
        // SSL 사용시 아래 라인 활성화, TLS만 사용시 주석 처리
        // properties.setProperty("mail.smtp.ssl.enable", "true");

        return properties;
    }
}
