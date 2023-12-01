package edu.pjatk.jcarsapi.repository;

import edu.pjatk.jcarsapi.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
}