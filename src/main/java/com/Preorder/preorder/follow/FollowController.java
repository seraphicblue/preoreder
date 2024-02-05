package com.preorder.preorder.follow;

import com.preorder.preorder.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
