package org.example.socialse2.service;

import org.example.socialse2.dto.RegistrationDto;
import org.example.socialse2.dto.UserDto;
import org.example.socialse2.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    void createUser(RegistrationDto registrationDto);

    User findByUsername(String username);

    void makeUserAdmin(String username);

    User findById(Long id);

    User getCurrentUser();

    void updateUser(UserDto user);

    boolean isAdmin(String username);

    boolean isOwnerOfPost(String username, Long postId);

    List<UserDto> getUsers();

    @Transactional
    void deleteUser(Long id);

    List<UserDto> searchUsers(String query);

}
