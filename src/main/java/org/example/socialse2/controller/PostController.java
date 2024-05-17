package org.example.socialse2.controller;

import jakarta.validation.Valid;
import org.example.socialse2.dto.PostDto;
import org.example.socialse2.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/admin/posts")
    private String posts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "admin/posts";
    }

    @GetMapping("/admin/posts/create")
    private String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("postDto", postDto);
        return "admin/create_post";
    }

    @PostMapping("/admin/posts/create")
    public String createPost(@Valid @ModelAttribute("postDto") PostDto postDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postDto", postDto);
            return "admin/create_post";
        }
        postService.createPost(postDto);
        log.info("Post created: {}", postDto.toString());
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/edit")
    private String editPostForm(@PathVariable Long postId, Model model) {
        PostDto postDto = postService.getPost(postId);
        model.addAttribute("postDto", postDto);
        return "admin/edit_post";
    }

    @PostMapping("/admin/posts/{postId}/edit")
    public String editPost(@PathVariable Long postId,
                           @ModelAttribute("postDto") PostDto postDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postDto", postDto);
            return "admin/edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        log.info("Post updated: {}", postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {
        PostDto postDto = postService.getPost(postId);
        model.addAttribute("postDto", postDto);
        return "admin/view_post";
    }

    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query, Model model) {
        List<PostDto> postDtoList = postService.searchPost(query);
        model.addAttribute("postDtoList", postDtoList);
        return "admin/posts";
    }

}
