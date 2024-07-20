package com.zancinema.Zancinema_official.convertor;

import com.zancinema.Zancinema_official.entities.User;
import com.zancinema.Zancinema_official.request.UserRequest;
import com.zancinema.Zancinema_official.response.UserResponse;

public class UserConvertor {

    public static User userDtoToUser(UserRequest userRequest, String encodedPassword) {
        return User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .address(userRequest.getAddress())
                .gender(userRequest.getGender())
                .mobileNo(userRequest.getMobileNo())
                .emailId(userRequest.getEmailId())
                .password(encodedPassword) // Use encodedPassword here
                .roles(userRequest.getRoles())
                .build();
    }

    public static UserResponse userToUserDto(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .age(user.getAge())
                .address(user.getAddress())
                .gender(user.getGender())
                .build();
    }
}
