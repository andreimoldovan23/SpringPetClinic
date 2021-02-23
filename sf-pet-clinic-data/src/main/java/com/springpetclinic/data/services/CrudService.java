package com.springpetclinic.data.services;

import java.util.Set;

public interface CrudService<T, ID> {
    T save(T type);
    Set<T> findAll();
    T findById(ID id);
    void delete(T type);
    void deleteById(ID id);
}
