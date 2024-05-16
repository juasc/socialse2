package org.example.socialse2.service;

import org.example.socialse2.dto.UserDto;
import org.example.socialse2.model.User;
import org.example.socialse2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole(), userDto.getFullname(), userDto.getPhoneNo(), userDto.getGender(), userDto.getDob());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }
}
