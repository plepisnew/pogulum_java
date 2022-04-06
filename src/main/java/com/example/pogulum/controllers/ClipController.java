package com.example.pogulum.controllers;

import com.example.pogulum.model.Clip;
import com.example.pogulum.services.ClipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClipController {

    ClipService clipService;

    public ClipController(ClipService clipService){
        this.clipService = clipService;
    }

    @PostMapping("/api/clip")
    public ResponseEntity<Clip> addClip(@RequestBody Clip clip){
        return new ResponseEntity<>(clipService.saveClip(clip), HttpStatus.OK);
    }

    @GetMapping("/api/clip")
    public ResponseEntity<Clip> getClipById(@RequestParam("id") String id){
        return new ResponseEntity<>(clipService.getClip(id), HttpStatus.OK);
    }

    @GetMapping("/api/clips")
    public ResponseEntity<List<Clip>> getClips(){
        return new ResponseEntity<>(clipService.getAllClips(), HttpStatus.OK);
    }




}
  
  
  
  
  
  
  
}
