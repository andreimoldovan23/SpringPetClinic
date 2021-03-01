package com.springpetclinic.data.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Owners")
public class Owner extends Person {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        pets.forEach(pet -> stringBuilder.append(pet.toString()).append(" "));
        return "Owner: " + getId() + ", " + getFirstName() + " " + getLastName() + ", " +
                phoneNumber + "\n" + address + "\nPets: " + stringBuilder.toString() + "\n";
    }

}
