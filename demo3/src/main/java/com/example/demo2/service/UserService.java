package com.example.demo2.service;


import com.example.demo2.entity.User;
import com.example.demo2.web.dto.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}