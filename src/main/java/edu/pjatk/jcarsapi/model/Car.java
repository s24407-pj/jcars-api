package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Column(name = "available", nullable = false)
    private Boolean isAvailable;
    @Column(nullable = false)
    private Integer milage;

    public Car() {
    }

    public Car(CarModel model, Integer year, String registrationPlate, BigDecimal rentalPrice, Boolean isAvailable, Integer milage) {
        this.model = model;
        this.year = year;
        this.registrationPlate = registrationPlate;
        this.rentalPrice = rentalPrice;
        this.isAvailable = isAvailable;
        this.milage = milage;
    }

    public Integer getId() {
        return id;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getMilage() {
        return milage;
    }

    public void setMilage(Integer milage) {
        this.milage = milage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(model, car.model) && Objects.equals(year, car.year) && Objects.equals(registrationPlate, car.registrationPlate) && Objects.equals(rentalPrice, car.rentalPrice) && Objects.equals(isAvailable, car.isAvailable) && Objects.equals(milage, car.milage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, year, registrationPlate, rentalPrice, isAvailable, milage);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model=" + model +
                ", year=" + year +
                ", registrationPlate='" + registrationPlate + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", isAvailable=" + isAvailable +
                ", milage=" + milage +
                '}';
    }
}