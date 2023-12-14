package edu.pjatk.jcarsapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "days_limit")
public class DaysLimit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer car_limit;



    public DaysLimit() {
    }

    public DaysLimit(Integer id, String name, Integer car_limit) {
        this.id = id;
        this.name = name;
        this.car_limit = car_limit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCar_limit() {
        return car_limit;
    }

    public void setCar_limit(Integer car_limit) {
        this.car_limit = car_limit;
    }
}
