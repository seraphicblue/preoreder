package com.preorder.preorder.post.response;

import java.io.Serializable;
import java.util.List;

public class FollowingeveryResponse implements Serializable {
    public Long userId;
    public List<CommentResponse> comments;
    public List<PostResponse> posts;
    public List<LikeResponse> likes;

    // 기본 생성자
    public FollowingeveryResponse() {
    }

    // 게터와 세터
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }

    public List<LikeResponse> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeResponse> likes) {
        this.likes = likes;
    }
}

