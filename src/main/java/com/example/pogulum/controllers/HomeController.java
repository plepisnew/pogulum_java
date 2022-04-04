package com.example.pogulum.controllers;

import com.example.pogulum.model.POJO;
import com.example.pogulum.util.Http;
import com.example.pogulum.util.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.http.HttpResponse;

@RestController
public class HomeController {

    @GetMapping("home")
    public String home() throws IOException, InterruptedException {
        HttpResponse<String> res = Http.getInstance().authGet("https://api.twitch.tv/helix/clips?game_id=497248");
        return res.body();
    }

}
