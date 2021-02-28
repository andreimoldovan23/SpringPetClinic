package com.springpetclinic.data.model;

public class Address extends Entity {

    private String city;
    private String streetLine;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetLine() {
        return streetLine;
    }

    public void setStreetLine(String streetLine) {
        this.streetLine = streetLine;
    }

    public String toString() {
        return "Address: " + getId() + ", " + city + ", " + streetLine;
    }

}
