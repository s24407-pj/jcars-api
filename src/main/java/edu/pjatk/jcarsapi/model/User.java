package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String secondName;
    //Zweryfikowane prawo jazdy
    private boolean isVerified;
    @Column(unique = true)
    private String email;
    private String phoneNumber;


    //TODO
    //role i haslo
}
