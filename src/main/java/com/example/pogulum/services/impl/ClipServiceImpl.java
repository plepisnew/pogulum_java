package com.example.pogulum.services.impl;
import com.example.pogulum.exceptions.UserNotFoundException;
import com.example.pogulum.model.Clip;
import com.example.pogulum.repository.ClipRepository;
import com.example.pogulum.services.ClipService;
import com.example.pogulum.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClipServiceImpl implements ClipService {

    private ClipRepository clipRepository;

    public ClipServiceImpl(ClipRepository clipRepository) {
        this.clipRepository = clipRepository;
    }

    @Override
    public Clip saveClip(Clip clip) {
        clipRepository.save(clip);
        return clip;
    }

    public Clip getClip(String id) {
         return clipRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

    }

    public List<Clip> getAllClips() {
        return clipRepository.findAll();
    }
}

