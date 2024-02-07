package com.preorder.preorder.post;

import com.preorder.preorder.post.entity.Comment;
import com.preorder.preorder.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    List<PostLike> findByUserIdIn(List<Long> userId);

    List<PostLike> findByUserId(Long followerId);
}
