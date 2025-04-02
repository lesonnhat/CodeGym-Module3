package org.example.bthienthidskhachhang;

public class Customer {
    private String name;
    private String birthdate;
    private String address;
    private String imageUrl;

    // Constructor, get, and set methods


    public Customer(String name, String birthdate, String address, String imageUrl) {
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}