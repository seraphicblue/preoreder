package com.preorder.preorder.post.response;

import java.io.Serializable;


public class PostResponse implements Serializable {

    public Long id;
    public String content;

    public PostResponse() {
        super();
    }


    public PostResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}

