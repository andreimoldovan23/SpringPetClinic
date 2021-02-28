package com.springpetclinic.data.exceptions;

public class PetWithoutName extends MyException {
    @Override
    public String toString() {
        return "Cannot save pet without name";
    }
}
