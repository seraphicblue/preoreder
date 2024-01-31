package com.preorder.preorder.service;

import java.util.List;

import com.preorder.preorder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class PasswordUpdateService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void encryptAndSaveAllPasswords() {
        // 1. 데이터베이스에서 모든 사용자 정보 가져오기
        List<User> users = userDao.findAll();

        for (User user : users) {
            // 2. 비밀번호 암호화
            String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

            // 3. 암호화된 비밀번호로 업데이트
            user.setPassword(encryptedPassword);
            userDao.save(user);
        }


    }
}

