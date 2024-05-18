package org.example.socialse2.service.impl;

import org.example.socialse2.dto.PostDto;
import org.example.socialse2.mapper.PostMapper;
import org.example.socialse2.model.Post;
import org.example.socialse2.repository.PostRepository;
import org.example.socialse2.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> getPosts() {
        return postRepository.findAll().stream().map(PostMapper::toDto).collect(Collectors.toList());
    }

    public void createPost(PostDto postDto) {
        Post post = PostMapper.toEntity(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto getPost(Long id) {
        return postRepository.findById(id)
                             .map(PostMapper::toDto)
                             .orElseThrow(() -> new NoSuchElementException("Post with id " + id + " not found"));
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = PostMapper.toEntity(postDto);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        return postRepository.searchPosts(query).stream().map(PostMapper::toDto).collect(Collectors.toList());
    }

}
