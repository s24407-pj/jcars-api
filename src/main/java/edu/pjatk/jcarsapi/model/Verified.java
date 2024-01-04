package edu.pjatk.jcarsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "verified")
public class Verified {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean emailIsVerified = false;
    private Boolean carLicenseIsVerified = false;
    private Boolean passwordIsChanged = false;
    private Boolean addressIsChanged = false;
    private Boolean phoneIsChanged = false;

    public Verified() {
    }

    public Verified(Boolean emailIsVerified, Boolean carLicenseIsVerified, Boolean passwordIsChanged, Boolean addressIsChanged, Boolean phoneIsChanged) {
        this.emailIsVerified = emailIsVerified;
        this.carLicenseIsVerified = carLicenseIsVerified;
        this.passwordIsChanged = passwordIsChanged;
        this.addressIsChanged = addressIsChanged;
        this.phoneIsChanged = phoneIsChanged;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEmailIsVerified() {
        return emailIsVerified;
    }

    public void setEmailIsVerified(Boolean emailIsVerified) {
        this.emailIsVerified = emailIsVerified;
    }

    public Boolean getCarLicenseIsVerified() {
        return carLicenseIsVerified;
    }

    public void setCarLicenseIsVerified(Boolean carLicenseIsVerified) {
        this.carLicenseIsVerified = carLicenseIsVerified;
    }

    public Boolean getPasswordIsChanged() {
        return passwordIsChanged;
    }

    public void setPasswordIsChanged(Boolean passwordIsChanged) {
        this.passwordIsChanged = passwordIsChanged;
    }

    public Boolean getAddressIsChanged() {
        return addressIsChanged;
    }

    public void setAddressIsChanged(Boolean addressIsChanged) {
        this.addressIsChanged = addressIsChanged;
    }

    public Boolean getPhoneIsChanged() {
        return phoneIsChanged;
    }

    public void setPhoneIsChanged(Boolean phoneIsChanged) {
        this.phoneIsChanged = phoneIsChanged;
    }
}
