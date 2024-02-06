package com.preorder.preorder.post.service;

import com.preorder.preorder.post.CommentRepository;
import com.preorder.preorder.post.PostRepository;
import com.preorder.preorder.post.entity.Comment;
import com.preorder.preorder.post.entity.Post;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(Long postId, String content) {
        // postId에 해당하는 Post를 찾습니다.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트의 아이디를 찾을 수 없습니다. " + postId));

        // 새 Comment 객체를 생성합니다.
        Comment comment = Comment.builder()
                .content(content)
                .post(post)
                .build();

        // Comment 객체를 데이터베이스에 저장합니다.
        return commentRepository.save(comment);
    }
}
