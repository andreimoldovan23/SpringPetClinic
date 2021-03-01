package com.springpetclinic.data.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Addresses")
public class Address extends BaseEntity {

    @Column(name = "city")
    private String city;

    @Column(name = "street")
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
