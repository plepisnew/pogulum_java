package com.example.pogulum.services;
import com.example.pogulum.model.Clip;
import com.example.pogulum.model.User;

import java.util.List;

public interface ClipService {
Clip saveClip(com.example.pogulum.model.Clip clip);

    Clip getClip(String id);

    List<Clip> getAllClips();

}
