package com.preorder.preorder.post.service;

import com.preorder.preorder.post.PostLikeRepository;
import com.preorder.preorder.post.PostRepository;
import com.preorder.preorder.post.entity.Post;
import com.preorder.preorder.post.entity.PostLike;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {
    private final PostLikeRepository likeRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostLikeService(PostLikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    public PostLike likePost(Long postId) {
        // postId에 해당하는 Post를 확인합니다.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("포스트의 아이디를 찾을 수 없습니다. " + postId));

        // 새로운 Like 객체를 생성합니다.
        PostLike newLike = new PostLike();
        newLike.setPost(post); // PostLike 엔티티에 Post 설정

        // Like 객체를 데이터베이스에 저장합니다.
        return likeRepository.save(newLike);
    }

}
