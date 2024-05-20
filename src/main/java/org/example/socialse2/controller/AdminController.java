package org.example.socialse2.controller;

import org.example.socialse2.service.PostService;
import org.example.socialse2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final PostService postService;

    private final UserService userService;

    public AdminController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminRedirect() {
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts")
    private String posts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "admin/posts";
    }

    @GetMapping("/admin/users")
    private String users(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin/users";
    }

}
