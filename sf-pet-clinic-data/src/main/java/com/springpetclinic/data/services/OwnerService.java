package com.springpetclinic.data.services;

import com.springpetclinic.data.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>{
    List<Owner> findAllByLastNameLike(String lastName);
}
