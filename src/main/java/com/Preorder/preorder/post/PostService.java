package com.preorder.preorder.post;

import com.preorder.preorder.follow.FollowService;
import com.preorder.preorder.post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final FollowService followService;
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository, FollowService followService) {
        this.postRepository = postRepository;
        this.followService = followService;
    }

    public Post createPost(Long userId, String content) {
        // 수정: userId를 받아 Post 객체를 생성합니다.
        Post newPost = Post.builder()
                .userId(userId)
                .content(content)
                .build();
        return postRepository.save(newPost);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByFollowingUsers(Long userId) {
        // 팔로잉하는 사용자들의 ID 목록을 조회
        List<Long> followingUserIds = followService.getFollowingUserIds(userId);

        // 팔로잉하는 사용자들이 작성한 포스트 조회
        return postRepository.findByUserIdIn(followingUserIds);
    }

    // 기타 메소드
}
