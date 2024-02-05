package com.preorder.preorder.follow;

import com.preorder.preorder.member.MemberEntity;
import com.preorder.preorder.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    public FollowServiceImpl(FollowRepository followRepository, MemberRepository memberRepository) {
        this.followRepository = followRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void follow(Long followerId, Long followingId) {
        MemberEntity follower = memberRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("팔로워가 없습니다."));
        MemberEntity following = memberRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("팔로잉이 없습니다."));

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(follow);
    }

    // 특정 사용자가 팔로우하는 사용자 목록을 조회
    public List<Follow> findFollowing(Long userId) {
        return FollowRepository.findFollowing(userId);
    }

    // 특정 사용자를 팔로우하는 사용자 목록을 조회
    public List<Follow> findFollowers(Long userId) {
        return FollowRepository.findFollower(userId);
    }


}
