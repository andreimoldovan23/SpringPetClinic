package com.springpetclinic.data.exceptions;

public class NullOwner extends NullObject {
    @Override
    public String toString() {
        return super.toString() + ": null owner";
    }
}
