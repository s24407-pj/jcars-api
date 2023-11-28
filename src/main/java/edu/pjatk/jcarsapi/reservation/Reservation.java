package edu.pjatk.jcarsapi.reservation;

import edu.pjatk.jcarsapi.car.Car;
import edu.pjatk.jcarsapi.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private Insurance insuranceType;
    private float totalPrice;
    private Status status;
}
