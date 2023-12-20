package edu.pjatk.jcarsapi;

import edu.pjatk.jcarsapi.model.Car;
import edu.pjatk.jcarsapi.model.CarBrand;
import edu.pjatk.jcarsapi.model.CarModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class JcarsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(JcarsApiApplication.class, args);
    }
}
