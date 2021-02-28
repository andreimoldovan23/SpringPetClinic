package com.springpetclinic.data.exceptions;

public class NullPetType extends NullObject {
    @Override
    public String toString() {
        return super.toString() + ": null pet type";
    }
}
