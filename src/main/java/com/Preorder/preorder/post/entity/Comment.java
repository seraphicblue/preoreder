package com.preorder.preorder.post.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor // JPA를 위한 기본 생성자
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // 댓글 내용

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정
    @JoinColumn(name = "post_id") // 외래키를 post_id 컬럼으로 매핑
    private Post post;

    @Builder
    // 생성자에서 postId 파라미터를 제거하고, Post 엔티티를 직접 받도록 수정
    public Comment(final String content, final Post post) {
        this.content = content;
        this.post = post;
    }
}
