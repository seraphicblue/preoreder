package com.preorder.preorder.Controller;

import com.preorder.preorder.Config.JwtTokenProvider;
import com.preorder.preorder.service.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final FollowService followService;
    private final JwtTokenProvider jwtTokenProvider;


    // 팔로우하는 기능
    @PostMapping("/{followingId}/follow")
    public ResponseEntity<?> followUser(@PathVariable Long followingId, HttpServletRequest request) {
        log.info("followuser");
        // "Bearer " 접두사를 제거하고 토큰 추출
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        // 토큰에서 팔로워 ID 추출
        Long followerId = Long.valueOf(jwtTokenProvider.getIdFromToken(token));
        return followService.followUser(followingId, followerId);
    }

    // 언팔로우 기능
    @PostMapping("/{followingId}/unfollow")
    public ResponseEntity<?> unfollowUser(@PathVariable Long followingId, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring("Bearer ".length());
        Long followerId = Long.valueOf(jwtTokenProvider.getIdFromToken(token));
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

