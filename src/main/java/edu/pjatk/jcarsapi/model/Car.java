package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel model;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "registration_plate", nullable = false)
    private String registrationPlate;

    @Column(name = "rental_price", nullable = false)
    private BigDecimal rentalPrice;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;
    @Column(nullable = false)
    private Integer milage;

    public Car() {
    }
}