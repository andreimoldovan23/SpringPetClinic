package com.springpetclinic.data.exceptions;

public class NullObject extends MyException {
    @Override
    public String toString() {
        return "Cannot save a null object";
    }
}
