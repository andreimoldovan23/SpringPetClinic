package com.springpetclinic.data.repositories;

import com.springpetclinic.data.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepo extends CrudRepository<Pet, Long> {
}
