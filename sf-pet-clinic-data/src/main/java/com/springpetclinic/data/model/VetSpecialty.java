package com.springpetclinic.data.model;

public class VetSpecialty extends Entity {

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
