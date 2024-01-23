package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.Car;
import edu.pjatk.jcarsapi.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jcars")
public class CarController {

    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id) {
        Optional<Car> car = carService.getById(id);

        if (car.isPresent()) {
            return ResponseEntity.ok(car.get());
        } else {
            throw new ResourceNotFoundException("Car not found");
        }
    }

    @GetMapping("/cars/model/{id}")
    public ResponseEntity<List<Car>> getCarByModelsId(@PathVariable Integer id) {

        List<Car> carList = carService.getAll();
        List<Car> updatedList = new ArrayList<>();

        carList.forEach(car -> {
            if (car.getModel().getId().equals(id)) {
                updatedList.add(car);
            }
        });

        return new ResponseEntity<>(updatedList, HttpStatus.OK);
    }

    @GetMapping("/cars/brand/{id}")
    public ResponseEntity<List<Car>> getCarByBrandId(@PathVariable Integer id) {

        List<Car> carList = carService.getAll();
        List<Car> updatedList = new ArrayList<>();

        carList.forEach(car -> {
            if (car.getModel().getBrand().getId().equals(id)) {
                updatedList.add(car);
            }
        });
        return ResponseEntity.ok(updatedList);
    }

    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car car) {

        //if (car.getName() == null || car.getCategory() == null) {
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        //}

        carService.save(car);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car car) {

        if (carService.getById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //  car.setId(id);

        carService.save(car);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id) {

        if (carService.getById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        carService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
