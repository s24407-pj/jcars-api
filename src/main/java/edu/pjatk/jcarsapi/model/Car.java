package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

import java.util.List;
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

    @Column(name = "registration_plate", nullable = false, length = 16)
    private String registrationPlate;

    @Column(name = "deposit", nullable = false)
    private Integer deposit;

    @Column(name = "rental_price", nullable = false)
    private Integer rentalPrice;

    @ManyToMany
    private List<Attribute> attributes;

    @Column(name = "available", nullable = false)
    private Boolean isAvailable = false;
    @Column(nullable = false)
    private Integer milage;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "price_per_day",nullable = false)
    private Integer pricePerDay;

    private String description;

    public Car(Integer id, CarModel model, Integer year, String registrationPlate, Integer deposit, Integer rentalPrice, List<Attribute> attributes, Boolean isAvailable, Integer milage, String imageUrl,Integer pricePerDay, String description) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.registrationPlate = registrationPlate;
        this.deposit = deposit;
        this.rentalPrice = rentalPrice;
        this.attributes = attributes;
        this.isAvailable = isAvailable;
        this.milage = milage;
        this.imageUrl = imageUrl;
        this.pricePerDay = pricePerDay;
        this.description = description;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Car() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Integer rentalPrice) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(model, car.model) && Objects.equals(year, car.year) && Objects.equals(registrationPlate, car.registrationPlate) && Objects.equals(rentalPrice, car.rentalPrice) && Objects.equals(isAvailable, car.isAvailable) && Objects.equals(milage, car.milage) && Objects.equals(imageUrl, car.imageUrl) && Objects.equals(description, car.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, year, registrationPlate, rentalPrice, isAvailable, milage, imageUrl, description);
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
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}