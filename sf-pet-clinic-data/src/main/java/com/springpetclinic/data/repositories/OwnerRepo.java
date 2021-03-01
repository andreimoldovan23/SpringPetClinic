package com.springpetclinic.data.repositories;

import com.springpetclinic.data.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepo extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
}
