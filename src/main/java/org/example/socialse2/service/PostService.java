package org.example.socialse2.service;

import org.example.socialse2.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getPosts();

    void createPost(PostDto post);

    PostDto getPost(Long id);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);

    List<PostDto> searchPost(String query);

}
