package com.preorder.preorder.follow;

import com.preorder.preorder.member.MemberEntity;
import com.preorder.preorder.member.MemberRepository;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("Follower not found"));
        MemberEntity following = memberRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("Following not found"));

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(follow);
    }
}
