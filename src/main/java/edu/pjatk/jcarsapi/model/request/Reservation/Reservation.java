package edu.pjatk.jcarsapi.model.request.Reservation;

import java.util.Date;
import java.util.List;

public class Reservation {
    private String address;
    private String carlicense;
    private String city;
    private String documentid;
    private String email;
    private Date endDate;
    private String firstname;
    private String message;
    private String nip;
    private String personID;
    private String phone;
    private String promotionCode;
    private Date startDate;
    private String zipcode;

    private List<Adds> adds;

    public Reservation() {
    }

    public Reservation(String address, String carlicense, String city, String documentid, String email, Date endDate, String firstname, String message, String nip, String personID, String phone, String promotionCode, Date startDate, String zipcode, List<Adds> adds) {
        this.address = address;
        this.carlicense = carlicense;
        this.city = city;
        this.documentid = documentid;
        this.email = email;
        this.endDate = endDate;
        this.firstname = firstname;
        this.message = message;
        this.nip = nip;
        this.personID = personID;
        this.phone = phone;
        this.promotionCode = promotionCode;
        this.startDate = startDate;
        this.zipcode = zipcode;
        this.adds = adds;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Adds> getAdds() {
        return adds;
    }

    public void setAdds(List<Adds> adds) {
        this.adds = adds;
    }
}
