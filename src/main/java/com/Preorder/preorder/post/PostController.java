package com.preorder.preorder.post;

import com.preorder.preorder.post.entity.Comment;
import com.preorder.preorder.post.entity.Post;
import com.preorder.preorder.post.service.CommentService;
import com.preorder.preorder.post.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final PostLikeService likeService;

    @Autowired
    public PostController(PostService postService, CommentService commentService, PostLikeService likeService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post.getUserId(), post.getContent());
        return ResponseEntity.ok(newPost);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<Post>> getPostsByFollowing(@PathVariable Long userId) {
        List<Post> posts = postService.getPostsByFollowingUsers(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody String content) {
        Comment newComment = commentService.createComment(postId, content);
        return ResponseEntity.ok(newComment);
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<?> likePost(@PathVariable Long postId) {
        likeService.likePost(postId);
        return ResponseEntity.ok().build();
    }
}
