package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.CarModel;
import edu.pjatk.jcarsapi.service.CarModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jcars")
public class CarModelController {
    CarModelService carModelService;

    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @GetMapping("/car-models")
    public ResponseEntity<List<CarModel>> getAllCarModels() {
        return new ResponseEntity<>(carModelService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/car-models/brand/{id}")
    public ResponseEntity<List<CarModel>> getAllCarModelsByBrand(@PathVariable Integer id) {

        List<CarModel> carModels = carModelService.getAll();

        List<CarModel> updateCarModels = new ArrayList<>();

        carModels.forEach(carModel -> {
            if (carModel.getBrand().getId().equals(id)) {
                updateCarModels.add(carModel);
            }
        });

        return new ResponseEntity<>(updateCarModels, HttpStatus.OK);
    }

    @DeleteMapping("/car-models/{id}")
    public ResponseEntity<Void> deleteCarBrand(@PathVariable Integer id) {

        if (carModelService.getById(id).isEmpty()) {
            throw new ResourceNotFoundException("Car model not found");
        }

        carModelService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/car-models")
    public ResponseEntity<?> addCarModel(@RequestBody CarModel carModel) {


        carModelService.save(carModel);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
