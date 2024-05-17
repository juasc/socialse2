package org.example.socialse2.mapper;

import org.example.socialse2.dto.PostDto;
import org.example.socialse2.model.Post;

public class PostMapper {

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                      .id(post.getId())
                      .title(post.getTitle())
                      .content(post.getContent())
                      .url(post.getUrl())
                      .imageUrl(post.getImageUrl())
                      .createdAt(post.getCreatedAt())
                      .shortDescription(post.getShortDescription())
                      .build();
    }

    public static Post mapToPost(PostDto postDto) {
        return Post.builder()
                   .id(postDto.getId())
                   .title(postDto.getTitle())
                   .content(postDto.getContent())
                   .url(postDto.getUrl())
                   .imageUrl(postDto.getImageUrl())
                   .createdAt(postDto.getCreatedAt())
                   .shortDescription(postDto.getShortDescription())
                   .build();
    }

}
