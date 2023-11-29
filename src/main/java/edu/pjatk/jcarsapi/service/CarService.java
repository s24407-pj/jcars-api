package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.Car;
import edu.pjatk.jcarsapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Optional<Car> getById(Integer id) {
        return carRepository.findById(id);
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }
}
