package com.example.pogulum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    private Long missingUserId;

    public UserNotFoundException(Long missingUserId){
        super(String.format("Twitch User with ID %s not found!", missingUserId));
        this.missingUserId = missingUserId;
    }

    public Long getMissingUserId(){
        return this.missingUserId;
    }
    
}
