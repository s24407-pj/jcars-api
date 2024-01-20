package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.Reservation;
import edu.pjatk.jcarsapi.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        Optional<Reservation> reservation = reservationService.getById(id);

        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        } else {
            throw new ResourceNotFoundException("Reservation not found");
        }
    }

    @PostMapping("/reservations")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {



        //reservationService.save(reservation);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {

        if (reservationService.getById(id).isEmpty()) {
            throw new ResourceNotFoundException("Reservation not found");
        }



        reservationService.save(reservation);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @DeleteMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {

        if (reservationService.getById(id).isEmpty()) {
            throw new ResourceNotFoundException("Reservation not found");
        }

        reservationService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
