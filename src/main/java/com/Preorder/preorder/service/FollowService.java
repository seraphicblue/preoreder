package com.preorder.preorder.service;

import com.preorder.preorder.model.Follow;
import com.preorder.preorder.model.User;
import com.preorder.preorder.repository.FollowRepository;
import com.preorder.preorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public ResponseEntity<String> followUser(Long followingId, Long followerId) {
        // followerId로 User 객체 찾기
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalStateException("팔로워를 찾을 수 없습니다."));

        // 자기 자신을 팔로우하는 경우 체크
        if (followingId.equals((long) follower.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("자기 자신을 팔로우할 수 없습니다.");
        }

        // 이미 팔로우 중인지 체크
        Optional<Follow> checkFollow = followRepository.findByFollowingIdAndFollowerId(followingId, (long) follower.getId());
        if (checkFollow.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 팔로우 중입니다.");
        }

        // followingId로 User 객체 찾기
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalStateException("팔로우할 사용자를 찾을 수 없습니다."));

        // 새로운 Follow 인스턴스 생성 및 저장
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        followRepository.save(follow);

        return ResponseEntity.ok(following.getId() + "님을 팔로우하였습니다.");
    }

    public ResponseEntity<?> unfollowUser(Long followingId, Long followerId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalStateException("팔로워를 찾을 수 없습니다."));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalStateException("팔로우할 사용자를 찾을 수 없습니다."));

        Optional<Follow> follow = followRepository.findByFollowingIdAndFollowerId(followingId, (long) follower.getId());
        if (follow.isPresent()) {
            followRepository.delete(follow.get());
            return ResponseEntity.ok().body(follower.getId() + "님이 " + following.getId() + "님을 언팔로우하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("팔로우 관계가 존재하지 않습니다.");
        }
    }

    public List<Object> getFollowers(Long userId) {
        return followRepository.findAllByFollowingId(userId).stream()
                .collect(Collectors.toList());
    }

    public List<Object> getFollowing(Long userId) {
        return followRepository.findAllByFollowerId(userId).stream()
                .collect(Collectors.toList());
    }
}
