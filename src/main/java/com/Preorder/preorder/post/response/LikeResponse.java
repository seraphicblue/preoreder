package com.preorder.preorder.post.response;

import java.io.Serializable;

public class LikeResponse implements Serializable {
    public Long postId;

    // 생성자
    public LikeResponse(Long postId) {
        this.postId = postId;
    }


    public Long getPostId() {
        return postId;
    }


    public void setPostId(Long postId) {
        this.postId = postId;
    }


}
