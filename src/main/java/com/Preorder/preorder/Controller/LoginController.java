package com.preorder.preorder.Controller;

import com.preorder.preorder.Config.JwtToken;
import com.preorder.preorder.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginUserService loginUserService;

    @PostMapping("/loginup")
    public ResponseEntity<?> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        try {
            // 사용자 인증 시도
            JwtToken token = loginUserService.login(email, password);
            String userEmailFromToken = loginUserService.extractUserFromToken(token);
            log.info(userEmailFromToken + "userEmailFromToken");
            // 인증 성공 및 토큰 반환
            return ResponseEntity.ok().body(token.getAccessToken());
        } catch (Exception e) {
            // 로그인 처리 중 예외 발생
            log.error("Login error: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Login failed due to server error.");
        }
    }
}
