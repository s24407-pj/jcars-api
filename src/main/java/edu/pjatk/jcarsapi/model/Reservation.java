package edu.pjatk.jcarsapi.model;

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
