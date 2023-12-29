package edu.pjatk.jcarsapi.repository;

import edu.pjatk.jcarsapi.model.Add;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddsRepository extends JpaRepository<Add, Integer> {
}
