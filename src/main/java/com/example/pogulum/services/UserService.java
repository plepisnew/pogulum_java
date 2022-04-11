package com.example.pogulum.services;

import com.example.pogulum.model.User;
import java.util.List;

public interface UserService {

    User saveUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    User deleteUser(Long id);

}
