package com.springpetclinic.data.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {

    private Set<VetSpecialty> specialties = new HashSet<>();

    public Set<VetSpecialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<VetSpecialty> specialties) {
        this.specialties = specialties;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        specialties.forEach(spec -> stringBuilder.append(spec.toString()).append(" "));
        return "Vet: " + getId() + ", " + getFirstName() + " " + getLastName() + "\nSpecialties: " +
                stringBuilder.toString() + "\n";
    }

}
