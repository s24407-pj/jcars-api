package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

@Entity
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Car car;

}
