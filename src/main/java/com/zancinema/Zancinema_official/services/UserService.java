package com.zancinema.Zancinema_official.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zancinema.Zancinema_official.convertor.UserConvertor;
import com.zancinema.Zancinema_official.entities.User;
import com.zancinema.Zancinema_official.exceptions.UserExist;
import com.zancinema.Zancinema_official.repositories.UserRepository;
import com.zancinema.Zancinema_official.request.UserRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserRequest userRequest) {
        if (userRepository.findByEmailId(userRequest.getEmailId()) != null) {
            throw new UserExist();
        }

        // Ensure userRequest.getPassword() returns a valid String
        String password = userRequest.getPassword(); // Ensure this is a String
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = UserConvertor.userDtoToUser(userRequest, encodedPassword);
        userRepository.save(user);
        return "User Saved Successfully";
    }
}
