package com.example.pogulum.controllers;

import com.example.pogulum.model.POJO;
import com.example.pogulum.model.User;
import com.example.pogulum.util.Http;
import com.example.pogulum.util.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.http.HttpResponse;

@Controller
public class HomeController {

    @GetMapping("home")
    public String home() throws IOException, InterruptedException {
        return "index";
    }

    @PostMapping("home")
    public String home(@RequestBody User user) throws IOException, InterruptedException {
        HttpResponse<String> res = Http.getInstance().post("home", user);
        return res.body();
    }

//    @GetMapping("test")
//    public ModelAndView test(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("");
//    }

}
