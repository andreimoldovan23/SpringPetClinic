package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullVisit;
import com.springpetclinic.data.exceptions.VisitWithoutPet;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.Visit;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.VisitService;
import org.springframework.stereotype.Service;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    private final PetService petService;

    public VisitMapService(PetService petService) {
        this.petService = petService;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Visit save(Visit visit) throws MyException {
        if(visit == null)
            throw new NullVisit();

        Pet pet = visit.getPet();
        if(pet == null)
            throw new VisitWithoutPet();

        if(pet.getId() == null)
            petService.save(pet);

        if(visit.getId() == null) {
            Long id = super.id.incrementAndGet();
            visit.setId(id);
        }
        super.map.put(visit.getId(), visit);
        return visit;
    }

}
