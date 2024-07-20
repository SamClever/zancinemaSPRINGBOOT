package com.zancinema.Zancinema_official.request;

import com.zancinema.Zancinema_official.enums.Gender;

import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private Integer age;
    private String address;
    private String mobileNo;
    private String emailId;
    private Gender gender;
    private String roles;
    private String password; // Add this field

    // Implement getter and setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
