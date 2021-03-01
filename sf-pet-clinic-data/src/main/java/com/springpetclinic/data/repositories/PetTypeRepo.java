package com.springpetclinic.data.repositories;

import com.springpetclinic.data.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepo extends CrudRepository<PetType, Long> {
}
