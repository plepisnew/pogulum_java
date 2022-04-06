package com.example.pogulum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    private Long missingUserId;
    private String missingClipId;
    public UserNotFoundException(Long missingUserId){
        super(String.format("Twitch User with ID %s not found!", missingUserId));
        this.missingUserId = missingUserId;
    }
    
    
    public UserNotFoundException(String missingClipId){
        super(String.format("Twitch User with ID %s not found!", missingClipId));
        this.missingClipId = missingClipId;
    }


    public Long getMissingUserId(){
        return this.missingUserId;
    }
    
      public String getMissingClipId(){return this.missingClipId;
    }
    
}
