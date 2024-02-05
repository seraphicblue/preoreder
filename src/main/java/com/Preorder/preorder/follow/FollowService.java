package com.preorder.preorder.follow;

import java.util.List;

public interface FollowService {
    void follow(Long follower, Long following);

    List<Follow> findFollowing(Long userId);

    List<Follow> findFollowers(Long userId);
}