package com.preorder.preorder.post;

import com.preorder.preorder.follow.FollowService;
import com.preorder.preorder.post.entity.Comment;
import com.preorder.preorder.post.entity.Post;
import com.preorder.preorder.post.entity.PostLike;
import com.preorder.preorder.post.response.CommentResponse;
import com.preorder.preorder.post.response.FollowingeveryResponse;
import com.preorder.preorder.post.response.LikeResponse;
import com.preorder.preorder.post.response.PostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostService {
    private final FollowService followService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    @Autowired
    public PostService(PostRepository postRepository, FollowService followService, CommentRepository commentRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.followService = followService;
        this.commentRepository = commentRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public Post createPost(Long userId, String content) {
        Post newPost = new Post(userId, content); // Post 클래스의 생성자 또는 빌더를 이용한 인스턴스 생성
        log.info("Creating new post for userId: {}, content: '{}'", userId, content); // 로그 기록
        return postRepository.save(newPost);
    }


    public List<FollowingeveryResponse> getActivitiesByFollowingUsers(Long userId) {
        List<Long> followingUserIds = followService.getFollowingUserIds(userId);
        List<FollowingeveryResponse> responses = new ArrayList<>();

        for (Long followingUserId : followingUserIds) {
            List<Comment> comments = commentRepository.findByUserIdsIn(followingUserIds);
            List<Post> posts = postRepository.findByUserIdIn(followingUserIds);
            List<PostLike> likes = postLikeRepository.findByUserIdIn(followingUserIds);

            FollowingeveryResponse response = new FollowingeveryResponse();
            response.setUserId(followingUserId);
            response.setComments(comments.stream().map(comment -> new CommentResponse(comment.getId(), comment.getContent())).collect(Collectors.toList()));
            response.setPosts(posts.stream().map(post -> new PostResponse(post.getId(), post.getContent())).collect(Collectors.toList()));
            response.setLikes(likes.stream().map(like -> new LikeResponse(like.getPost().getId())).collect(Collectors.toList()));

            responses.add(response);
        }

        return responses;
    }


}


