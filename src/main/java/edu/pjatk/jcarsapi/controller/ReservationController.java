package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.Car;
import edu.pjatk.jcarsapi.model.Reservation;
import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.model.request.Reservation.ReservationRequest;
import edu.pjatk.jcarsapi.repository.UserRepository;
import edu.pjatk.jcarsapi.service.CarService;
import edu.pjatk.jcarsapi.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jcars")
public class ReservationController {
    private final ReservationService reservationService;

    private final UserRepository userRepository;

    private final CarService carService;

    public ReservationController(ReservationService reservationService, UserRepository userRepository, CarService carService) {
        this.reservationService = reservationService;
        this.userRepository = userRepository;
        this.carService = carService;
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
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationRequest reservationRequest) {

        Optional<User> user = userRepository.findByEmail(reservationRequest.getReservation().getEmail());

        if (user.isPresent()) {
            if (user.get().getFirstName().equals(reservationRequest.getReservation().getFirstname()) && user.get().getLastName().equals(reservationRequest.getReservation().getLastname())) {
                Optional<Car> car = carService.getById(reservationRequest.getReservation().getCarId());
                Reservation reservation = new Reservation();
                reservation.setCar(car.get());
                reservation.setUser(user.get());
                reservation.setStartDate(reservationRequest.getReservation().getStartDate());
                reservation.setEndDate(reservationRequest.getReservation().getEndDate());
                reservation.setStatus(Reservation.ReservationStatus.PAID);
                reservation.setTotalPrice(reservationRequest.getReservation().getTotal());
                reservation.setPaidCard(reservationRequest.getReservation().getCardPay());
                reservation.setAdds(reservationRequest.getAdds());
                reservationService.save(reservation);
                return ResponseEntity.ok(reservation);
            } else {
                throw new ResourceAccessException("Sorry data is uncorrected");
            }
        } else {
            throw new ResourceNotFoundException("Sorry that user isn't our customer");
        }
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
