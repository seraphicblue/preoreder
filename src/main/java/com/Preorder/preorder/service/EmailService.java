package com.Preorder.preorder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 생성자 주입 사용하기
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${email.verification.url}")
    private String verificationUrlBase;

    public String sendVerificationEmail(String username, String email, String token) {
        String verificationUrl = verificationUrlBase + "?token=" + token;
        String subject = "이메일 인증";
        String text = "이메일 인증을 위해 다음 링크를 클릭해주세요: " + verificationUrl;

        sendEmail(email, subject, text);

        return token; // 데이터베이스에 저장할 수 있도록 토큰 반환
    }

    public void sendEmail(String recipient, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient); // 수신자 설정
        message.setSubject(subject); // 제목 설정
        message.setText(text); // 내용 설정
        javaMailSender.send(message); // 메일 전송
    }
}
