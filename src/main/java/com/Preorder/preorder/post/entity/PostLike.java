package com.preorder.preorder.post.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Setter;

@Entity
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @Setter
    private Post post; // JPA 관계 매핑을 사용하여 Post 엔티티와의 연결

    public PostLike() {
        // 기본 생성자
    }

    @Builder
    public PostLike(Post post) {
        this.post = post;
    }


}
