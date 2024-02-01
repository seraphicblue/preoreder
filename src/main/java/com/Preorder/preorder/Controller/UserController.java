package com.preorder.preorder.Controller;

import com.preorder.preorder.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final FollowService followService;

    public UserController(FollowService followService) {
        this.followService = followService;
    }

    // 팔로우하는 기능
    @PostMapping("/{followerId}/follow/{followingId}")
    public ResponseEntity<?> followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        return followService.followUser(followingId, followerId);
    }

    // 언팔로우 기능
    @PostMapping("/{followerId}/unfollow/{followingId}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        return followService.unfollowUser(followingId, followerId);
    }

    // 사용자의 팔로워 조회
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<Object>> getFollowers(@PathVariable Long userId) {
        List<Object> followers = followService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    // 사용자의 팔로잉 조회
    @GetMapping("/{userId}/following")
    public ResponseEntity<List<Object>> getFollowing(@PathVariable Long userId) {
        List<Object> following = followService.getFollowing(userId);
        return ResponseEntity.ok(following);
    }
}
