package com.preorder.preorder.service;


import com.preorder.preorder.dao.UserDao;
import com.preorder.preorder.model.User;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    //private final PasswordEncoder passwordEncoder;

    public User createUser(String username, String email, String password, String token) {
        // Encrypt the password
       // String encryptedPassword = passwordEncoder.encode(password);

        // Create the user
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setToken(token);
        user.setVerify(false); // Initially, user is not verified

        // Save the user to the database
        userDao.save(user);

        return user;
    }

    public User verifyUser(String token) {
        // Find the user by token
        User user = userDao.findByToken(token);

        if (user != null && !user.isVerify()) {
            // Update user's verified status
            user.setVerify(true);
            userDao.save(user);
        }

        return user;
    }

}
