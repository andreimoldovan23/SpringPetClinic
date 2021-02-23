package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Pet;
import java.util.Set;

public class PetMapService extends AbstractMapService<Pet, Long> {

    public Pet save(Pet pet) {
        return super.save(pet);
    }

    public Set<Pet> findAll() {
        return super.findAll();
    }

    public void deleteById(Long id) {
        super.deleteById(id);
    }

    public void delete(Pet pet) {
        super.delete(pet);
    }

    public Pet findById(Long id) {
        return super.findById(id);
    }

}
