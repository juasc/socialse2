package org.example.socialse2.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.socialse2.dto.CommentDto;
import org.example.socialse2.mapper.CommentMapper;
import org.example.socialse2.model.Comment;
import org.example.socialse2.model.Post;
import org.example.socialse2.repository.CommentRepository;
import org.example.socialse2.repository.PostRepository;
import org.example.socialse2.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void addComment(Long postId, CommentDto commentDto) {
        postRepository.findById(postId).ifPresent(post -> {
            Comment comment = CommentMapper.toEntity(commentDto);
            comment.setPost(post);
            commentRepository.save(comment);
        });
    }

    @Override
    public List<CommentDto> getComments(Long postId) {
        Post post = postRepository.findById(postId)
                                  .orElseThrow(() -> new EntityNotFoundException("Post with id " +
                                                                                 postId +
                                                                                 " not found"));
        return commentRepository.findByPost(post).stream().map(CommentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public boolean commentBelongsToPost(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                                           .orElseThrow(() -> new EntityNotFoundException("Comment not found with id " +
                                                                                          commentId));
        return comment.getPost().getId().equals(postId);
    }

}
