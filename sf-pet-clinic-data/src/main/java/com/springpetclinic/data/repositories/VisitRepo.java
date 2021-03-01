package com.springpetclinic.data.repositories;

import com.springpetclinic.data.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepo extends CrudRepository<Visit, Long> {
}
