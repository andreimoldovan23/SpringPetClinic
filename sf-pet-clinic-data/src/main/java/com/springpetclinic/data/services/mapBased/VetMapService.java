package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Vet;
import java.util.Set;

public class VetMapService extends AbstractMapService<Vet, Long> {

    public Vet save(Vet vet) {
        return super.save(vet);
    }

    public Set<Vet> findAll() {
        return super.findAll();
    }

    public void deleteById(Long id) {
        super.deleteById(id);
    }

    public void delete(Vet vet) {
        super.delete(vet);
    }

    public Vet findById(Long id) {
        return super.findById(id);
    }

}
