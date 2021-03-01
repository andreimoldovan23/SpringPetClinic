package com.springpetclinic.data.repositories;

import com.springpetclinic.data.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
