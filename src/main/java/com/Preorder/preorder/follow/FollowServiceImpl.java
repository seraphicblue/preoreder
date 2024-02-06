package com.preorder.preorder.follow;

import com.preorder.preorder.member.Member;
import com.preorder.preorder.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public FollowServiceImpl(FollowRepository followRepository, MemberRepository memberRepository) {
        this.followRepository = followRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void follow(Long followerId, Long followingId) {
        Member follower = memberRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("팔로워가 없습니다."));
        Member following = memberRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("팔로잉이 없습니다."));

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(follow);
    }

    @Override
    public List<Follow> findFollowing(Long userId) {

        return followRepository.findFollowing(userId);
    }

    @Override
    public List<Follow> findFollowers(Long userId) {

        return followRepository.findFollower(userId);
    }

    @Override
    public List<Long> getFollowingUserIds(Long userId) {
        List<Follow> follows = followRepository.findFollowing(userId);
        return follows.stream().map(follow -> follow.getFollowing().getId()).collect(Collectors.toList());
    }


}
