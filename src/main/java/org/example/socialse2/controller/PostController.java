package org.example.socialse2.controller;

import jakarta.validation.Valid;
import org.example.socialse2.dto.CommentDto;
import org.example.socialse2.dto.PostDto;
import org.example.socialse2.service.CommentService;
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

    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/posts/create")
    private String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("postDto", postDto);
        return "create_post";
    }

    @PostMapping("/posts/create")
    public String createPost(@Valid @ModelAttribute("postDto") PostDto postDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postDto", postDto);
            return "create_post";
        }
        postService.createPost(postDto);
        log.info("Post created: {}", postDto.toString());
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}/edit")
    private String editPostForm(@PathVariable Long postId, Model model) {
        PostDto postDto = postService.getPost(postId);
        model.addAttribute("postDto", postDto);
        return "edit_post";
    }

    @PostMapping("/posts/{postId}/edit")
    public String editPost(@PathVariable Long postId,
                           @ModelAttribute("postDto") PostDto postDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postDto", postDto);
            return "edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        log.info("Post updated: {}", postDto);
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {
        PostDto postDto = postService.getPost(postId);
        model.addAttribute("postDto", postDto);
        model.addAttribute("commentDto", new CommentDto());
        model.addAttribute("comments", commentService.getComments(postId));
        return "view_post";
    }

    @PostMapping("/posts/{postId}/comments")
    public String addComment(@PathVariable Long postId, @ModelAttribute("commentDto") CommentDto commentDto) {
        commentService.addComment(postId, commentDto);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/posts/{postId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable Long postId,
                                @PathVariable Long commentId,
                                @ModelAttribute("commentDto") CommentDto commentDto) {
        commentService.deleteComment(commentId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query, Model model) {
        log.info("Searching posts: {}", query);
        List<PostDto> postDtoList = postService.searchPosts(query);
        model.addAttribute("posts", postDtoList);
        return "index";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "index";
    }

}
