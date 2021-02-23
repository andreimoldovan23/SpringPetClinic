package com.springpetclinic.data.services;

import com.springpetclinic.data.model.Pet;
import java.util.Set;

public interface PetService {

    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();

}
