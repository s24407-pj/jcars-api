package edu.pjatk.jcarsapi.car;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Brand brand;
    @Enumerated(EnumType.STRING)
    private Model model;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    private float pricePerDay;
    @Enumerated(EnumType.STRING)
    private CarClass carClass;
    private int seats;
    @Column(nullable = false)
    private int horsePower;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    private int yearOfProduction;
    private int mileage;
    private boolean isAvailable;
    private Localization localization;


}
