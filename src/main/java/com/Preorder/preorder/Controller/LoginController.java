package com.preorder.preorder.Controller;

import java.util.List;
import java.util.Map;

import com.preorder.preorder.Config.JwtToken;
import com.preorder.preorder.dao.UserDao;
import com.preorder.preorder.model.User;
import com.preorder.preorder.service.PasswordUpdateService;
import com.preorder.preorder.service.LoginUserService;
import lombok.extern.slf4j.Slf4j;
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


@Slf4j
@Controller
public class LoginController {

    private final LoginUserService loginUserService;
    private final PasswordUpdateService pwservice;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDao userDao;


    public LoginController(LoginUserService loginUserService, PasswordUpdateService pwservice,UserDao userDao) {
        this.loginUserService = loginUserService;
        this.pwservice = pwservice;
        this.userDao=userDao;

    }

    @PostMapping("/loginup")
    public String loginSuccess(
            @RequestParam("email") String email,
            @RequestParam("password") String password, Model model) {
         User user = userDao.findByEmail(email);
         log.info("dd");
         String username = user.getUsername();

        JwtToken token = loginUserService.login(username, password); //토큰 생성

        System.out.println(token+"token");

        // 토큰에서 사용자 정보(이메일)를 추출
        String userEmailFromToken = loginUserService.extractUserFromToken(token);
        System.out.println(userEmailFromToken+"userEmailFromToken");

        return "mainpage";

    }




}

