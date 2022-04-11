package com.example.pogulum.controllers;

import com.example.pogulum.model.User;
import com.example.pogulum.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping("/api/user")
    public ResponseEntity<User> getUserById(@RequestParam("id") Long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/api/user")
    public ResponseEntity<User> deleteUserById(@RequestParam("id") Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }


}
