package edu.pjatk.jcarsapi.repository;

import edu.pjatk.jcarsapi.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributesRepository extends JpaRepository<Attribute, Integer> {
}
