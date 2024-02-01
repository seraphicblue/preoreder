package com.preorder.preorder.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
@Data
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // getter 메소드들 (필요한 경우에 따라 추가)

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower; // 팔로워


    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following; // 팔로잉

}
