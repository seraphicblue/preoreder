package com.preorder.preorder.follow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowCreate {

    private Long followerMemberId;
    private Long followingMemberId;

    @Builder
    public FollowCreate(final Long followerMemberId, final Long followingMemberId) {
        this.followerMemberId = followerMemberId;
        this.followingMemberId = followingMemberId;
    }

    // followingMemberId 필드에 대한 게터 메서드
    public Long getFollowingId() {
        return followingMemberId;
    }

    // followingMemberId 필드에 대한 세터 메서드
    public void setFollowingId(Long followingId) {
        this.followingMemberId = followingId;
    }
}