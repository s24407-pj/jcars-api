package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    @Column(nullable = false, unique = true, length = 32)
    private String email;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @Column(length = 64)
    private String address;

    @Column(name = "has_driving_license", nullable = false)
    private Boolean hasDrivingLicense = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_verified", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "verified_id"))
    private Verified verified;


    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String phoneNumber, String address, Boolean hasDrivingLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.hasDrivingLicense = hasDrivingLicense;
    }

    public Verified getVerified() {
        return verified;
    }

    public void setVerified(Verified verified) {
        this.verified = verified;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHasDrivingLicense() {
        return hasDrivingLicense;
    }

    public void setHasDrivingLicense(Boolean hasDrivingLicense) {
        this.hasDrivingLicense = hasDrivingLicense;
    }
}