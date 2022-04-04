package com.example.pogulum.services;

import com.example.pogulum.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

//    User updateUser(Long id, User user);
//
//    User deleteUser(Long id);
}
