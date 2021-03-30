package com.springpetclinic.data.repositories;

import com.springpetclinic.data.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepo extends CrudRepository<Owner, Long> {
    List<Owner> findAllByLastNameLike(String lastName);
}
