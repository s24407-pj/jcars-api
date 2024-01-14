package edu.pjatk.jcarsapi.model.request.Reservation;

import java.time.LocalDateTime;

public class Reservation {
    private Integer carId;
    private String address;
    private String carlicense;
    private String city;
    private String documentid;
    private String email;
    private LocalDateTime endDate;
    private String firstname;
    private String message;
    private String nip;
    private String personid;
    private String phone;
    private String lastname;
    private String promotionCode;
    private LocalDateTime startDate;
    private String zipcode;
    private Boolean cardPay;
    private Double total;

    public Reservation() {
    }

    public Reservation(Integer carId, String address, String carlicense, String city, String documentid, String email, LocalDateTime endDate, String firstname, String message, String nip, String personid, String phone, String lastname, String promotionCode, LocalDateTime startDate, String zipcode, Boolean cardPay, Double total) {
        this.carId = carId;
        this.address = address;
        this.carlicense = carlicense;
        this.city = city;
        this.documentid = documentid;
        this.email = email;
        this.endDate = endDate;
        this.firstname = firstname;
        this.message = message;
        this.nip = nip;
        this.personid = personid;
        this.phone = phone;
        this.lastname = lastname;
        this.promotionCode = promotionCode;
        this.startDate = startDate;
        this.zipcode = zipcode;
        this.cardPay = cardPay;
        this.total = total;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarlicense() {
        return carlicense;
    }

    public void setCarlicense(String carlicense) {
        this.carlicense = carlicense;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Boolean getCardPay() {
        return cardPay;
    }

    public void setCardPay(Boolean cardPay) {
        this.cardPay = cardPay;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
