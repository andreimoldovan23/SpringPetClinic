package com.springpetclinic.data.exceptions;

public class NonExistentType extends MyException {
    @Override
    public String toString() {
        return "Pet type does not exist";
    }
}
