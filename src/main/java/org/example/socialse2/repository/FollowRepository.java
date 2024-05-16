package org.example.socialse2.repository;

import org.example.socialse2.model.Follows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follows, Long> {
    @Query("SELECT COUNT(f) FROM Follows f WHERE f.followerId = ?1")
    int countByFollowerId(Long followerId);

    @Query("SELECT COUNT(f) FROM Follows f WHERE f.followingId = ?1")
    int countByFollowingId(Long followingId);
}