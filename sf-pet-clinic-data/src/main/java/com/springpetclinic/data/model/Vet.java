package com.springpetclinic.data.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Vets")
public class Vet extends Person {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "VetsSpecialties",
        joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
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
