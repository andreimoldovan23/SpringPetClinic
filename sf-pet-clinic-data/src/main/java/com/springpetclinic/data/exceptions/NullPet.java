package com.springpetclinic.data.exceptions;

public class NullPet extends NullObject {
    @Override
    public String toString() {
        return super.toString() + ": null pet";
    }
}
