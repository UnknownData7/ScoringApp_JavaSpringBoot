package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserActivityController {
    private final static List<User> users = new ArrayList<>();

    @PostMapping("")
    public User addUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @GetMapping("")
    public List<User> getUsers() {
        return users;
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return users.stream().filter(user -> user.getUsername().equals(username))
                .findFirst().get();
    }

    @PutMapping("/{username}")
    public Post update(@PathVariable("username") String username, @RequestBody Post post) {
        users.stream().filter(user ->
                        user.getUsername().equals(username))
                .findAny()
                .ifPresent(user -> user.getPosts().add(post));
        return post;
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        users.stream().filter(user ->
                        user.getUsername().equals(username))
                .findAny()
                .ifPresent(users::remove);
        return "User with username: " + username + " has been deleted";
    }

}