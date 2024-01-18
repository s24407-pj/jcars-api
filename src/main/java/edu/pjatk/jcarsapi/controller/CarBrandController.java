package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.CarBrand;
import edu.pjatk.jcarsapi.service.CarBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CarBrandController {
    CarBrandService carBrandService;

    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @GetMapping("/car-brands")
    public ResponseEntity<List<CarBrand>> getAllCarBrands() {
        return new ResponseEntity<>(carBrandService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/car-brands/{id}")
    public ResponseEntity<Void> deleteCarBrand(@PathVariable Integer id) {

        if (carBrandService.getById(id).isEmpty()) {
            throw new ResourceNotFoundException("Car brand not found");
        }

        carBrandService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/car-brands")
    public ResponseEntity<?> addCarBrand(@RequestBody CarBrand carBrand) {


        carBrandService.save(carBrand);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
