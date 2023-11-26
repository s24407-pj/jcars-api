package edu.pjatk.jcarsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class JcarsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcarsApiApplication.class, args);
    }

//pozniej do wyjebania, tak dla testu czy dziala
    @GetMapping("/ping")
    String pingPongController(){
        return "pong";
    }

}
