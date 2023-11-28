package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String brandName;

   /* @OneToMany(mappedBy = "brand")
    private Set<Model> models;*/
}