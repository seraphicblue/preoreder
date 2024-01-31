package com.preorder.preorder.Controller;

import com.preorder.preorder.model.User;
import com.preorder.preorder.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    @PostMapping("/{followerId}/follow/{followingId}")
    public ResponseEntity<?> followUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        userService.followUser(followerId, followingId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{followerId}/unfollow/{followingId}")
    public ResponseEntity<?> unfollowUser(@PathVariable Long followerId, @PathVariable Long followingId) {
        userService.unfollowUser(followerId, followingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Long userId) {
        List<User> followers = userService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Long userId) {
        List<User> following = userService.getFollowing(userId);
        return ResponseEntity.ok(following);
    }
}