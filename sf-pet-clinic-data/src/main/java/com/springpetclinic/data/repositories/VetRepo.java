package com.springpetclinic.data.repositories;

import com.springpetclinic.data.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepo extends CrudRepository<Vet, Long> {
}
