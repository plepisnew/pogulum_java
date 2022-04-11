package com.example.pogulum.services.impl;

import com.example.pogulum.exceptions.UserNotFoundException;
import com.example.pogulum.model.User;
import com.example.pogulum.repository.UserRepository;
import com.example.pogulum.services.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return user;
    }

}
