package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.model.Car;
import edu.pjatk.jcarsapi.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars()
    {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id) {
        Car car = carService.getCarById(id);

        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car car) {

        //if (car.getName() == null || car.getCategory() == null) {
       //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //}

        carService.saveCar(car);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car car) {

        if (carService.getCarById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        car.setId(id);

        carService.saveCar(car);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {

        if (carService.getCarById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        carService.deleteCarById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
