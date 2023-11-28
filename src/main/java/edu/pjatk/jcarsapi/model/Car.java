package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Transient
    private Brand brand;
    @Transient
    private Model model;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    private Float pricePerDay;
    @Enumerated(EnumType.STRING)
    private CarCategory carCategory;
    private Integer seats;
    private Integer horsePower;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    private Integer yearOfProduction;
    private Integer mileage;
    private Boolean isAvailable;
    private Localization localization;

    public Model getModel() {
        return model;
    }

    public Brand getBrand() {
        return brand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
