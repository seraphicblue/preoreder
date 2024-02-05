package com.preorder.preorder.follow;


import com.preorder.preorder.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;


@Entity
@Table(name = "follows")
@Getter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower", referencedColumnName = "id")
    private MemberEntity follower;
    @ManyToOne
    @JoinColumn(name = "following", referencedColumnName = "id")
    private MemberEntity following;


    @Builder
    public Follow(
            final Long id,
            final MemberEntity follower,
            final MemberEntity following

    ) {
        this.id = id;
        this.follower = follower;
        this.following = following;

    }

    public static Follow create(final MemberEntity follower, final MemberEntity following) {
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }


}