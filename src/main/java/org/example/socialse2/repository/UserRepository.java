package org.example.socialse2.repository;

import org.example.socialse2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE " +
           "u.username LIKE CONCAT('%', :query,'%') OR " +
           "u.firstName LIKE CONCAT('%', :query,'%') OR " +
           "u.lastName LIKE CONCAT('%', :query,'%') OR " +
           "u.email LIKE CONCAT('%', :query,'%') OR " +
           "u.bio LIKE CONCAT('%', :query,'%') OR " +
           "u.website LIKE CONCAT('%', :query,'%')")
    List<User> searchUsers(String query);

}
