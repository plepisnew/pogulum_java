package com.example.pogulum.exceptions;

public class ClipNotFoundException extends RuntimeException {

    private String missingClipId;

    public ClipNotFoundException(String missingClipId){
        super(String.format("Twitch Game with ID %s not found!", missingClipId));
        this.missingClipId = missingClipId;
    }

    public String getMissingUserId(){
        return this.missingClipId;
    }
}
