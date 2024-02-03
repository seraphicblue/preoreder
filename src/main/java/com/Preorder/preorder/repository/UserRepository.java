package com.preorder.preorder.repository;

import com.preorder.preorder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByToken(String token);

    User findByUsername(String username);

    User findByUserid(String username);
}