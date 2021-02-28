package com.springpetclinic.data.exceptions;

public class NullVet extends NullObject {
    @Override
    public String toString() {
        return super.toString() + ": null vet";
    }
}
