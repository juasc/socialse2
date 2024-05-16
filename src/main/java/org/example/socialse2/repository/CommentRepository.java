package org.example.socialse2.repository;

import org.example.socialse2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.post.id = ?1")
    List<Comment> findCommentsByPostId(Long id);
}