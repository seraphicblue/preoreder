package com.preorder.preorder.service;


import com.preorder.preorder.model.Follow;
import com.preorder.preorder.repository.FollowRepository;
import com.preorder.preorder.model.User;
import com.preorder.preorder.repository.UserRepository;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    private FollowRepository followRepository;

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
        userRepository.save(user);

        return user;
    }

    public User verifyUser(String token) {
        // Find the user by token
        User user = userRepository.findByToken(token);

        if (user != null && !user.isVerify()) {
            // Update user's verified status
            user.setVerify(true);
            userRepository.save(user);
        }

        return user;
    }

}
