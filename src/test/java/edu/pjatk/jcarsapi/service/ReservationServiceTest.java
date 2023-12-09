package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.*;
import edu.pjatk.jcarsapi.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {


    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService underTest;

    @Test
    void getAll() {
        CarModel dummyModel = new CarModel(1,"name",new CarBrand(1,"name"));
        Car dummyCar = new Car(1, dummyModel, 2000, "ABC-123", 500, true, 100000, "https/test.com/image", "This is a test car");
        User dummyUser = new User(1, "John", "Doe", "john.doe@example.com", "password123", "1234567890", "123 Sample Street", true);
        Reservation reservation1 = new Reservation(1,dummyUser,dummyCar, LocalDateTime.now(),LocalDateTime.MAX, Reservation.ReservationStatus.ACTIVE);
        Reservation reservation2 = new Reservation(2,dummyUser,dummyCar, LocalDateTime.now(),LocalDateTime.MAX, Reservation.ReservationStatus.CANCELED);
        List<Reservation> expectedReservations = List.of(reservation1, reservation2);

        when(reservationRepository.findAll()).thenReturn(expectedReservations);

        List<Reservation> actualReservations = underTest.getAll();

        assertEquals(expectedReservations, actualReservations);
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        CarModel dummyModel = new CarModel(1,"name",new CarBrand(1,"name"));
        Car dummyCar = new Car(1, dummyModel, 2000, "ABC-123", 500, true, 100000, "https/test.com/image", "This is a test car");
        User dummyUser = new User(1, "John", "Doe", "john.doe@example.com", "password123", "1234567890", "123 Sample Street", true);
        Reservation expectedReservation = new Reservation(1,dummyUser,dummyCar, LocalDateTime.now(),LocalDateTime.MAX, Reservation.ReservationStatus.ACTIVE);
        int id = expectedReservation.getId();
        when(reservationRepository.findById(id)).thenReturn(Optional.of(expectedReservation));

        Optional<Reservation> actualReservation = underTest.getById(id);

        assertEquals(Optional.of(expectedReservation), actualReservation);
        verify(reservationRepository, times(1)).findById(id);
    }

    @Test
    void save() {
        CarModel dummyModel = new CarModel(1,"name",new CarBrand(1,"name"));
        Car dummyCar = new Car(1, dummyModel, 2000, "ABC-123", 500, true, 100000, "https/test.com/image", "This is a test car");
        User dummyUser = new User(1, "John", "Doe", "john.doe@example.com", "password123", "1234567890", "123 Sample Street", true);
        Reservation reservation = new Reservation(1,dummyUser,dummyCar, LocalDateTime.now(),LocalDateTime.MAX, Reservation.ReservationStatus.ACTIVE);

        underTest.save(reservation);

        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void deleteById() {
        Integer id = 1;

        underTest.deleteById(id);

        verify(reservationRepository, times(1)).deleteById(id);
    }
}