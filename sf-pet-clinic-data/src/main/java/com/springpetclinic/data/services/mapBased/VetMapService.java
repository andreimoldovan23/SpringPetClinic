package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullSpecialty;
import com.springpetclinic.data.exceptions.NullVet;
import com.springpetclinic.data.exceptions.VetWithoutSpecialties;
import com.springpetclinic.data.model.Vet;
import com.springpetclinic.data.model.VetSpecialty;
import com.springpetclinic.data.services.VetService;
import com.springpetclinic.data.services.VetSpecialtyService;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final VetSpecialtyService vetSpecialtyService;

    public VetMapService(VetSpecialtyService vetSpecialtyService) {
        this.vetSpecialtyService = vetSpecialtyService;
    }

    @Override
    public Vet save(Vet vet) throws MyException {
        if(vet == null)
            throw new NullVet();

        Set<VetSpecialty> specialties = vet.getSpecialties();
        if(specialties.size() == 0)
            throw new VetWithoutSpecialties();

        for(var spec: specialties) {
            if(spec == null)
                throw new NullSpecialty();
            if(spec.getId() == null)
                vetSpecialtyService.save(spec);
        }

        if(vet.getId() == null) {
            Long id = super.id.incrementAndGet();
            vet.setId(id);
        }
        super.map.put(vet.getId(), vet);
        return vet;
    }

}
