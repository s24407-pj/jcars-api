package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.*;
import edu.pjatk.jcarsapi.model.Enums.ERoles;
import edu.pjatk.jcarsapi.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
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
    private Reservation dummyReservation1;
    private Reservation dummyReservation2;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService underTest;

    @BeforeEach
    void setUp() {
        Car car = new Car(
                1,
                new CarModel(1, "name", new CarBrand(1, "name")),
                2022,
                "ABC123",
                500,
                50,
                List.of(new Attribute(1, "name"), new Attribute(2, "name2")),
                true,
                10000,
                "https://dummy.com/car",
                40,
                "This is a dummy car"
        );
        CarModel model = new CarModel(1, "name", new CarBrand(1, "name"));
        User user = new User(1, "John", "Doe", "john.doe@example.com", "password123", "1234567890", "123 Sample Street", true, ERoles.ROLE_USER);
        dummyReservation1 = new Reservation(1, user, car, LocalDateTime.now(), LocalDateTime.MAX, Reservation.ReservationStatus.ACTIVE);
        dummyReservation2 = new Reservation(2, user, car, LocalDateTime.now(), LocalDateTime.MAX, Reservation.ReservationStatus.CANCELED);
    }

    @Test
    void getAll() {
        Reservation reservation1 = dummyReservation1;
        Reservation reservation2 = dummyReservation2;
        List<Reservation> expectedReservations = List.of(reservation1, reservation2);

        when(reservationRepository.findAll()).thenReturn(expectedReservations);

        List<Reservation> actualReservations = underTest.getAll();

        assertEquals(expectedReservations, actualReservations);
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        Reservation expectedReservation = dummyReservation1;
        int id = expectedReservation.getId();
        when(reservationRepository.findById(id)).thenReturn(Optional.of(expectedReservation));

        Optional<Reservation> actualReservation = underTest.getById(id);

        assertEquals(Optional.of(expectedReservation), actualReservation);
        verify(reservationRepository, times(1)).findById(id);
    }

    @Test
    void save() {
        Reservation reservation = dummyReservation1;

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