package com.example.pogulum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

//    @GetMapping("/home")
//    public ModelAndView home(){
//        return new ModelAndView("index.html");
//    }
    @GetMapping("/home")
    public String home(){
        return "index";
    }
}
