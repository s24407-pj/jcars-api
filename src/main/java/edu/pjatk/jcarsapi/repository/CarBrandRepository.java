package edu.pjatk.jcarsapi.repository;

import edu.pjatk.jcarsapi.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrand, Integer> {
}