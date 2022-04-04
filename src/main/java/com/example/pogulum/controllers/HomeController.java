package com.example.pogulum.controllers;

import com.example.pogulum.model.POJO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    @GetMapping
    public POJO home(){
        return new POJO("Sample", 1337);
    }

    public POJO home(@RequestParam("name") String name, @RequestParam("id") int id){
        return new POJO(name, id);
    }

}
