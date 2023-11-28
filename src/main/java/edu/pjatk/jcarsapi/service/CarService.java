package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.Car;
import edu.pjatk.jcarsapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Integer id) {
        return carRepository.findById(id).orElse(null);
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public void deleteCarById(Integer id) {
        carRepository.deleteById(id);
    }
}
