package org.example.socialse2.service;

import org.example.socialse2.dto.CommentDto;

import java.util.List;

public interface CommentService {

    void addComment(Long postId, CommentDto commentDto);

    List<CommentDto> getComments(Long postId);

    void deleteComment(Long commentId);

    boolean commentBelongsToPost(Long postId, Long commentId);

}
