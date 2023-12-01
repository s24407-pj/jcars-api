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

@RestController
@SpringBootApplication
public class JcarsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(JcarsApiApplication.class, args);
    }

    @GetMapping("/carss")
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(List.of(
                new Car(1, new CarModel(1, "Accura", new CarBrand(1, "Honda")), 2000, "GST13123", 715, true, 23333,"https://media.istockphoto.com/id/1157655660/photo/generic-red-suv-on-a-white-background-side-view.jpg?s=612x612&w=0&k=20&c=ecrvXZhimUHnYES-kx7L5b-TDtRU5kAFPpNm0ZtAp1Y=" ,"Some description of car.adsgfsagasedrgserdgsergesgsergsergesrge"),
                new Car(2, new CarModel(2, "Civic", new CarBrand(1, "Honda")), 2002, "GST12223", 730, true, 24433, "https://media.istockphoto.com/id/1157655660/photo/generic-red-suv-on-a-white-background-side-view.jpg?s=612x612&w=0&k=20&c=ecrvXZhimUHnYES-kx7L5b-TDtRU5kAFPpNm0ZtAp1Y=","Some description of car.adsgfsagasedrgserdgsergesgsergsergesrge"),
                new Car(3, new CarModel(2, "Civic", new CarBrand(1, "Honda")), 2002, "GST12223", 730, true, 24433,"https://media.istockphoto.com/id/1157655660/photo/generic-red-suv-on-a-white-background-side-view.jpg?s=612x612&w=0&k=20&c=ecrvXZhimUHnYES-kx7L5b-TDtRU5kAFPpNm0ZtAp1Y=" ,"Some description of car.adsgfsagasedrgserdgsergesgsergsergesrge"),
                new Car(4, new CarModel(2, "Civic", new CarBrand(1, "Honda")), 2002, "GST12223", 730, true, 24433,"https://media.istockphoto.com/id/1157655660/photo/generic-red-suv-on-a-white-background-side-view.jpg?s=612x612&w=0&k=20&c=ecrvXZhimUHnYES-kx7L5b-TDtRU5kAFPpNm0ZtAp1Y=" ,"Some description of car.adsgfsagasedrgserdgsergesgsergsergesrge"),
                new Car(5, new CarModel(2, "Civic", new CarBrand(1, "Honda")), 2002, "GST12223", 730, true, 24433, "https://media.istockphoto.com/id/1157655660/photo/generic-red-suv-on-a-white-background-side-view.jpg?s=612x612&w=0&k=20&c=ecrvXZhimUHnYES-kx7L5b-TDtRU5kAFPpNm0ZtAp1Y=","Some description of car.adsgfsagasedrgserdgsergesgsergsergesrge"),
                new Car(6, new CarModel(2, "Civic", new CarBrand(1, "Honda")), 2002, "GST12223", 730, true, 24433,"https://media.istockphoto.com/id/1157655660/photo/generic-red-suv-on-a-white-background-side-view.jpg?s=612x612&w=0&k=20&c=ecrvXZhimUHnYES-kx7L5b-TDtRU5kAFPpNm0ZtAp1Y=" ,"Some description of car.adsgfsagasedrgserdgsergesgsergsergesrge")),
                HttpStatus.OK);
    }
}
