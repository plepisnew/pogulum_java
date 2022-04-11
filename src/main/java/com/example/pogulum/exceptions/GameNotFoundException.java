package com.example.pogulum.exceptions;

public class GameNotFoundException extends RuntimeException {

    private Long missingGameId;

    public GameNotFoundException(Long missingGameId){
        super(String.format("Twitch Game with ID %s not found!", missingGameId));
        this.missingGameId = missingGameId;
    }

    public Long getMissingUserId(){
        return this.missingGameId;
    }
}
