package com.springpetclinic.data.exceptions;

public class OwnerWithoutAddress extends MyException {
    @Override
    public String toString() {
        return "Cannot save owner without address";
    }
}
