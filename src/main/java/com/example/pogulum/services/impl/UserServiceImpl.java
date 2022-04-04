package com.example.pogulum.services.impl;

import com.example.pogulum.repository.UserRepository;
import com.example.pogulum.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

}
