package com.preorder.preorder.service;

import com.preorder.preorder.Config.JwtToken;
import com.preorder.preorder.Config.JwtTokenProvider;
import com.preorder.preorder.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        private final BCryptPasswordEncoder encoder;

        private final AuthenticationManagerBuilder authenticationManagerBuilder;
        private final JwtTokenProvider jwtTokenProvider;

        private String username;

        public LoginUserService(BCryptPasswordEncoder encoder, UserDao userDao,
                           AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
            this.encoder = encoder;
            this.userDao = userDao;
            this.authenticationManagerBuilder = authenticationManagerBuilder;
            this.jwtTokenProvider = jwtTokenProvider;
        }

        public JwtToken login(String email, String password) {
            // Authentication 객체 생성
            log.info("로그인 도착");
            UserDetails userDetails = null;
            this.username = email;
            try {
                // 사용자 정보 가져오기
                userDetails = userDetailsService.loadUserByUsername(username);
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
            if (!encoder.matches(password, userDetails.getPassword())) {
                System.out.println(password);
                System.out.println(userDetails.getPassword());
                throw new BadCredentialsException("Invalid password");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    password);
            System.out.println(username + password);
            System.out.println(authenticationToken);

            try {
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                log.info("Authentication successful: {}", authentication);

                // 검증된 인증 정보로 JWT 토큰 생성 및 반환
                return jwtTokenProvider.generateToken(authentication);
            } catch (Exception e) {
                log.error("Authentication failed for user: {}", email, e);
                throw new BadCredentialsException("Authentication failed");
            }
        }

        public String extractUserFromToken(JwtToken token) {
            //JwtToken 객체에서 실제 토큰 문자열을 반환
            System.out.println(jwtTokenProvider.getUsernameFromToken(token.getAccessToken()));
            return jwtTokenProvider.getUsernameFromToken(token.getAccessToken());

        }

    }

