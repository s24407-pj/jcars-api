package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String modelName;

    @ManyToOne
    private Brand brand;
}