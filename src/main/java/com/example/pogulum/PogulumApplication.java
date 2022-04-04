package com.example.pogulum;

import com.example.pogulum.model.POJO;
import com.example.pogulum.util.Http;
import com.example.pogulum.util.Json;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PogulumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PogulumApplication.class, args);

//		curl -X GET 'https://api.twitch.tv/helix/users?login=twitchdev' \
//		-H 'Authorization: Bearer gpjco2dprfnmsek34k9e1xqm02pweg' \
//		-H 'Client-Id: 0kvjan2jt8lf8qkhjolubt5ggih7ip'



	}

}
