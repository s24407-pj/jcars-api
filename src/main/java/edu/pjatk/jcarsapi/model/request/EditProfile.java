package edu.pjatk.jcarsapi.model.request;

public class EditProfile {
    private String address;
    private String phone;

    public EditProfile() {
    }

    public EditProfile(String address, String phone) {
        this.address = address;
        this.phone = phone;
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
