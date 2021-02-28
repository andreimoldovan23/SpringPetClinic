package com.springpetclinic.data.services;

import com.springpetclinic.data.exceptions.MyException;

import java.util.Set;

public interface CrudService<T, ID> {
    T save(T type) throws MyException;
    Set<T> findAll();
    T findById(ID id);
    void delete(T type);
    void deleteById(ID id);
}
