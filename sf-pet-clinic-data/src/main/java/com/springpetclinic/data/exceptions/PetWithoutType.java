package com.springpetclinic.data.exceptions;

public class PetWithoutType extends MyException {
    @Override
    public String toString() {
        return "Cannot save a pet without type";
    }
}
