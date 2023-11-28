package edu.pjatk.jcarsapi.repository;

import edu.pjatk.jcarsapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
