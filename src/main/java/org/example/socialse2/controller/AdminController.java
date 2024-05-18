package org.example.socialse2.controller;

import org.example.socialse2.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final PostService postService;

    public AdminController(PostService postService) {
        this.postService = postService;
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

}
