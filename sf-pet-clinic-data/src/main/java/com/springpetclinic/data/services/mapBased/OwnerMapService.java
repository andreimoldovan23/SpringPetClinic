package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Owner;
import java.util.Set;

public class OwnerMapService extends AbstractMapService<Owner, Long> {

    public Owner save(Owner owner) {
        return super.save(owner);
    }

    public Set<Owner> findAll() {
        return super.findAll();
    }

    public Owner findById(Long id) {
        return super.findById(id);
    }

    public void delete(Owner owner) {
        super.delete(owner);
    }

    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
