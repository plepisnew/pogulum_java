package com.example.pogulum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pogulum.model.Clip;
import com.example.pogulum.model.User;
public interface ClipRepository extends JpaRepository<Clip, String> {
}
