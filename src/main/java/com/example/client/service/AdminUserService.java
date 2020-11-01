package com.example.client.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.client.model.User;
import com.example.client.dto.UserRegistrationDto;

public interface AdminUserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
