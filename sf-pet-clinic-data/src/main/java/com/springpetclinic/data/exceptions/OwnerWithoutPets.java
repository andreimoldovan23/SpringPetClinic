package com.springpetclinic.data.exceptions;

public class OwnerWithoutPets extends MyException {
    @Override
    public String toString() {
        return "Cannot save an owner without pets";
    }
}
