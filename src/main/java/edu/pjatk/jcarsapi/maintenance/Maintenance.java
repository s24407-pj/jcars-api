package edu.pjatk.jcarsapi.maintenance;

import edu.pjatk.jcarsapi.car.Car;
import jakarta.persistence.*;

@Entity
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Car car;

}
