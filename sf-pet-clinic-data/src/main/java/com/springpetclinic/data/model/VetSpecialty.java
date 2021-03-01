package com.springpetclinic.data.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Specialties")
public class VetSpecialty extends BaseEntity {

    @Column(name = "name")
    private String specialtyName;

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String toString() {
        return "{ " + getId() + ", " + specialtyName + " }";
    }

}
