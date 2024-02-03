package com.preorder.preorder.service;

import com.preorder.preorder.Config.JwtToken;
import com.preorder.preorder.Config.JwtTokenProvider;
import com.preorder.preorder.model.User;
import com.preorder.preorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String username;
    private String userid;

    public JwtToken login(String email, String password) {
        // Authentication 객체 생성
        log.info("jwtlogin");
        UserDetails userDetails = null;
        this.username = email;
        // User finduser = userRepository.findByUserid(userid);

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
        // 비밀번호 검증 로직
        try {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                // 비밀번호가 일치하는 경우
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
                log.info("Authentication successful: {}", authentication);

                // 검증된 인증 정보로 JWT 토큰 생성 및 반환
                return jwtTokenProvider.generateToken(authentication);
            } else {
                // 비밀번호가 일치하지 않는 경우
                log.error("Authentication failed for user: {}", username);
                throw new BadCredentialsException("Invalid password");
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
