package com.springpetclinic.data.exceptions;

public class NullVisit extends NullObject {
    @Override
    public String toString() {
        return super.toString() + ": null visit";
    }
}
