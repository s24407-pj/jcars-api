package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.Reservation;
import edu.pjatk.jcarsapi.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getById(Integer id) {
       return reservationRepository.findById(id);
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }
}
