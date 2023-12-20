package edu.pjatk.jcarsapi.repository;

import edu.pjatk.jcarsapi.model.Enums.ERoles;
import edu.pjatk.jcarsapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERoles roles);
}
