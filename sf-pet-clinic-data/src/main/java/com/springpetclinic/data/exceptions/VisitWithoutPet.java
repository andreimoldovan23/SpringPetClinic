package com.springpetclinic.data.exceptions;

public class VisitWithoutPet extends MyException {
    @Override
    public String toString() {
        return "Cannot save visit without pet";
    }
}
