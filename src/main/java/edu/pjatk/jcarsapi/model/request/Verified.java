package edu.pjatk.jcarsapi.model.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Verified {

    private Integer id;
    private Boolean emailIsVerified;
    private Boolean carLicenseIsVerified;
    private Boolean passwordIsChanged;
    private Boolean addressIsChanged;
    private Boolean phoneIsChanged;

    public Verified() {
    }

    public Verified(Integer id, Boolean emailIsVerified, Boolean carLicenseIsVerified, Boolean passwordIsChanged, Boolean addressIsChanged, Boolean phoneIsChanged) {
        this.id = id;
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
