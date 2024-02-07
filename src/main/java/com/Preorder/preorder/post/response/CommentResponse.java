
package com.preorder.preorder.post.response;

import java.io.Serializable;

public class CommentResponse implements Serializable {

    public Long commentId;
    public String content;

    // Constructor
    public CommentResponse(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }

    // Getters
    public Long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    // Setters
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
