package edu.pjatk.jcarsapi.model.response;


import edu.pjatk.jcarsapi.model.Verified;

import java.util.List;

public class JwtResponse {
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String email;
    private List<String> roles;
    private Verified verified;


    public JwtResponse(String firstname, String lastname, String address, String phone, String token, Integer id, String email, List<String> roles, Verified verified) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.token = token;
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.verified = verified;
    }

    public Verified getVerified() {
        return verified;
    }

    public void setVerified(Verified verified) {
        this.verified = verified;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
