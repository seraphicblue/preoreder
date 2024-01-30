package com.preorder.preorder.Controller;

import java.util.List;
import java.util.Map;

import com.preorder.preorder.Config.JwtToken;
import com.preorder.preorder.service.PasswordUpdateService;
import com.preorder.preorder.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Controller
public class LoginController {

    private final LoginUserService loginUserService;
    private final PasswordUpdateService pwservice;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public LoginController(LoginUserService loginUserService, PasswordUpdateService pwservice) {
        this.loginUserService = loginUserService;
        this.pwservice = pwservice;
    }

    @PostMapping("/loginup")
    public String loginSuccess(
            @RequestParam("email") String email,
            @RequestParam("password") String password, Model model) {
        System.out.println(email);

        JwtToken token = loginUserService.login(email, password); //토큰 생성

        System.out.println(token);

        // 토큰에서 사용자 정보(이메일)를 추출
        String userEmailFromToken = loginUserService.extractUserFromToken(token);
        System.out.println(userEmailFromToken+"userEmailFromToken");

        return "mainpage";

    }




}

