package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.CarModel;
import edu.pjatk.jcarsapi.repository.CarModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelService {
    CarModelRepository carModelRepository;

    public CarModelService(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public List<CarModel> getAll() {
        return carModelRepository.findAll();
    }

    public Optional<CarModel> getById(Integer id) {
        return carModelRepository.findById(id);
    }

    public void deleteById(Integer id) {
        carModelRepository.deleteById(id);
    }

    public void save(CarModel carModel) {
        carModelRepository.save(carModel);
    }
}
