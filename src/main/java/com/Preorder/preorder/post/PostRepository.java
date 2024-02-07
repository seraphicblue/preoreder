package com.preorder.preorder.post;

import com.preorder.preorder.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserIdIn(List<Long> userIds);

    List<Post> findByUserId(Long followerId);
}