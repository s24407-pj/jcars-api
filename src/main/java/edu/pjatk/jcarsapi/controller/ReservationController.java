package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.Reservation;
import edu.pjatk.jcarsapi.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jcars")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        Optional<Reservation> reservation = reservationService.getById(id);

        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        } else {
            throw new ResourceNotFoundException("Reservation not found");
        }
    }

    @PostMapping("/reservations")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {



        reservationService.save(reservation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {

        if (reservationService.getById(id).isEmpty()) {
            throw new ResourceNotFoundException("Reservation not found");
        }



        reservationService.save(reservation);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {

        if (reservationService.getById(id).isEmpty()) {
            throw new ResourceNotFoundException("Reservation not found");
        }

        reservationService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
