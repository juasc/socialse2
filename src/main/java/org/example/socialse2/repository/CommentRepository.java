package org.example.socialse2.repository;

import org.example.socialse2.model.Comment;
import org.example.socialse2.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findByPostId(Long postId);

}
