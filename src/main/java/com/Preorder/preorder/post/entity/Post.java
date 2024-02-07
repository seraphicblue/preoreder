package com.preorder.preorder.post.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 활성화
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자용, protected로 설정
@AllArgsConstructor // 모든 필드를 포함한 생성자 생성
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "user_id")
    private Long userId; // 포스트 작성자 ID

    private String content; // 포스트 내용

    @CreatedDate // 생성 시간 자동 설정
    private LocalDateTime createdAt;


    @Builder
    // 생성자에서 postId 파라미터를 제거하고, Post 엔티티를 직접 받도록 수정
    public Post(final Long userId, final String content) {
        this.userId = userId;
        this.content = content;
    }


}
