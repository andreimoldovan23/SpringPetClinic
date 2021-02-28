package com.springpetclinic.data.exceptions;

public class NullSpecialty extends NullObject {
    @Override
    public String toString() {
        return super.toString() + ": null specialty";
    }
}
