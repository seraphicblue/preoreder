package com.preorder.preorder.follow;

import com.preorder.preorder.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(final FollowService followService) {
        this.followService = followService;
    }

    //팔로우 하기
    @PostMapping
    public ResponseEntity<Void> follow(
            @RequestBody final FollowCreate followCreate,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        if (userDetails == null) {
            // 사용자가 인증되지 않았을 경우의 처리 로직
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        followService.follow(userDetails.getId(), followCreate.getFollowingId());
        return ResponseEntity.ok().build();
    }

    // 특정 사용자가 팔로우하는 사용자 목록을 조회하는 엔드포인트
    @GetMapping("/following/{userId}")
    public ResponseEntity<List<Follow>> getFollowing(@PathVariable Long userId) {
        List<Follow> following = followService.findFollowing(userId);
        return ResponseEntity.ok(following);
    }

    // 특정 사용자를 팔로우하는 사용자 목록을 조회하는 엔드포인트
    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<Follow>> getFollowers(@PathVariable Long userId) {
        List<Follow> followers = followService.findFollowers(userId);
        return ResponseEntity.ok(followers);
    }

}
