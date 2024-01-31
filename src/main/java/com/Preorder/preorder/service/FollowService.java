package com.preorder.preorder.service;

import com.preorder.preorder.model.Follow;
import com.preorder.preorder.model.User;
import com.preorder.preorder.repository.FollowRepository;
import com.preorder.preorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public ResponseEntity<String> followUser(Long followingId, User follower) {
        if (followingId.equals(follower.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("자기 자신을 팔로우할 수 없습니다.");
        }

        Optional<Follow> checkFollow = followRepository.findByFollowingIdAndFollowerId(followingId, follower.getId());
        if (checkFollow.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 팔로우 중입니다.");
        }

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalStateException("팔로우할 사용자를 찾을 수 없습니다."));

        Follow follow = new Follow(follower, following);
        followRepository.save(follow);

        return ResponseEntity.ok(following.getNickname() + "님을 팔로우하였습니다.");
    }
}
