package org.example.socialse2.repository;

import org.example.socialse2.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT c FROM Post c WHERE c.status LIKE %?1%")
    Page<Post> findByPostIdContainingIgnoreCase(String keyword, Pageable pageable);
}