package edu.pjatk.jcarsapi.repository;

import edu.pjatk.jcarsapi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
