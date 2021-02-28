package com.springpetclinic.data.exceptions;

public class VetWithoutSpecialties extends MyException {
    @Override
    public String toString() {
        return "Cannot save vet without specialties";
    }
}
