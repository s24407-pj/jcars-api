package edu.pjatk.jcarsapi.user;

import jakarta.persistence.*;

@Entity
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
