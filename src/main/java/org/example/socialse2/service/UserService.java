package org.example.socialse2.service;

import org.example.socialse2.dto.RegistrationDto;
import org.example.socialse2.model.User;

public interface UserService {

    void createUser(RegistrationDto registrationDto);

    User findByUsername(String username);

    void makeUserAdmin(String username);

}
