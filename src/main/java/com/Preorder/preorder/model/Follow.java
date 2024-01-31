package com.preorder.preorder.model;

import jakarta.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower; // 팔로워
    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following; // 팔로잉


}