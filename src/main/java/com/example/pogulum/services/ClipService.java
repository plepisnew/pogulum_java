package com.example.pogulum.services;
import com.example.pogulum.model.Clip;
import com.example.pogulum.model.User;

import java.util.List;

public interface ClipService {

    Clip saveClip(Clip clip);

    Clip getClipById(String id);

    List<Clip> getAllClips();

    Clip deleteClipById(String id);

}
