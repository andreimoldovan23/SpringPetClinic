package com.springpetclinic.data.services;

import com.springpetclinic.data.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
