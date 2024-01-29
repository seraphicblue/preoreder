package com.Preorder.preorder.Controller;

import com.Preorder.preorder.model.User;
import com.Preorder.preorder.service.EmailService;
import com.Preorder.preorder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @PostMapping("/signup")
    public ResponseEntity<?> signup(String username, String email, String password) {
        try {
            String token = UUID.randomUUID().toString();
            User user = userService.createUser(username, email, password, token);
            emailService.sendVerificationEmail(username, email, token);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Error during signup", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        try {
            User user = userService.verifyUser(token);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Error during email verification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
