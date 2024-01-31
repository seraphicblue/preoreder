package com.preorder.preorder.service;

import com.preorder.preorder.Config.JwtToken;
import com.preorder.preorder.Config.JwtTokenProvider;
import com.preorder.preorder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LoginUserService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserDao userDao;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginUserService(AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtToken login(String username, String password) {
        // Authentication 객체 생성
        log.info("jwtlogin");
        UserDetails userDetails = null;

        User findUser;
        try {
            // 사용자 정보 가져오기
            findUser = userDao.findByUsername(username);

        } catch (UsernameNotFoundException e) {
            // 사용자가 존재하지 않을 경우 처리
            log.info("User not found: " + username);
            throw new BadCredentialsException("Invalid email");
        } catch (Exception e) {
            // 다른 예외 발생 시 처리
            log.info("Error loading user details: " + e.getMessage());
            throw new RuntimeException("Error occurred while fetching user details", e);
        }

        // 이후 로직
        try {
            // 사용자 입력 비밀번호를 해싱하여 데이터베이스에 저장된 해싱된 비밀번호와 비교
            if (passwordEncoder.matches(password, findUser.getPassword())) {
                // 비밀번호가 일치하는 경우
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
                log.info("Authentication successful: {}", authentication);

                // 검증된 인증 정보로 JWT 토큰 생성 및 반환
                return jwtTokenProvider.generateToken(authentication);
            } else {
                // 비밀번호가 일치하지 않는 경우
                log.error("Authentication failed for user: {}", username);
                throw new BadCredentialsException("Authentication failed");
            }
        } catch (Exception e) {
            log.error("Authentication failed for user: {}", username, e);
            throw new BadCredentialsException("Authentication failed");
        }
    }

    public String extractUserFromToken(JwtToken token) {
        // JwtToken 객체에서 실제 토큰 문자열을 반환
        System.out.println(jwtTokenProvider.getUsernameFromToken(token.getAccessToken()));
        return jwtTokenProvider.getUsernameFromToken(token.getAccessToken());
    }
}
