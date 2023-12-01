package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.CarBrand;
import edu.pjatk.jcarsapi.repository.CarBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarBrandService {
    CarBrandRepository carBrandRepository;

    public CarBrandService(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    public List<CarBrand> getAll() {
        return carBrandRepository.findAll();
    }

    public Optional<CarBrand> getById(Integer id) {
        return carBrandRepository.findById(id);
    }

    public void deleteById(Integer id) {
        carBrandRepository.deleteById(id);
    }

    public void save(CarBrand carBrand) {
        carBrandRepository.save(carBrand);
    }
}
