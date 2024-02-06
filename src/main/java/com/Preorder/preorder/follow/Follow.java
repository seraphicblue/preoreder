package com.preorder.preorder.follow;

import com.preorder.preorder.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "follows")
@Getter
@NoArgsConstructor // 이를 추가하여 기본 생성자를 자동으로 생성
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower", referencedColumnName = "id")
    private Member follower;

    @ManyToOne
    @JoinColumn(name = "following", referencedColumnName = "id")
    private Member following;

    @Builder
    public Follow(final Long id, final Member follower, final Member following) {
        this.id = id;
        this.follower = follower;
        this.following = following;
    }


}
