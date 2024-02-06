package com.preorder.preorder.post;

import com.preorder.preorder.post.entity.Comment;
import com.preorder.preorder.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
