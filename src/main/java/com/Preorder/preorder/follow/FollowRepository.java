package com.preorder.preorder.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT f FROM Follow f JOIN FETCH f.following m WHERE f.follower.id = :id")
    List<Follow> findFollowing(@Param("id") Long userId);


    @Query("SELECT f FROM Follow f JOIN FETCH f.follower m WHERE f.following.id = :id")
    List<Follow> findFollower(@Param("id") Long userId);


    @Query("SELECT f FROM Follow f WHERE f.follower.id = :followerId AND f.following.id = :followingId")
    Optional<Follow> findByFollowerAndFollowing(@Param("followerId") Long followerId, @Param("followingId") Long followingId);

    List<Follow> findByFollowerId(Long userId);
}


