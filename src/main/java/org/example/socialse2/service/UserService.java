package org.example.socialse2.service;

import org.example.socialse2.dto.UserDto;
import org.example.socialse2.model.User;

public interface UserService {

    User save(UserDto userDto);

}
