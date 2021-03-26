package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullPet;
import com.springpetclinic.data.exceptions.PetWithoutName;
import com.springpetclinic.data.exceptions.PetWithoutType;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.model.Visit;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.PetTypeService;
import com.springpetclinic.data.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

    private final PetTypeService petTypeService;
    private final VisitService visitService;

    public PetMapService(PetTypeService petTypeService, VisitService visitService) {
        this.petTypeService = petTypeService;
        this.visitService = visitService;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Pet save(Pet pet) throws MyException {
        if(pet == null)
            throw new NullPet();
        if(pet.getName() == null)
            throw new PetWithoutName();

        PetType petType = pet.getType();
        if(petType == null)
            throw new PetWithoutType();
        petTypeService.save(petType);

        if(pet.getId() == null) {
            Long id = super.id.incrementAndGet();
            pet.setId(id);
        }

        Set<Visit> newVisits = new HashSet<>();
        for(var v: pet.getVisits()) {
            v.setPet(pet);
            newVisits.add(visitService.save(v));
        }
        pet.setVisits(newVisits);

        super.map.put(pet.getId(), pet);
        return pet;
    }

    private void deleteCascade(Pet pet) {
        List<Visit> visits = visitService.findAll().stream()
                .filter(v -> pet.getVisits().contains(v))
                .collect(Collectors.toList());
        visits.forEach(visitService::delete);
    }

    @Override
    public void delete(Pet type) {
        deleteCascade(type);
        super.delete(type);
    }

    @Override
    public void deleteById(Long aLong) {
        deleteCascade(findById(aLong));
        super.deleteById(aLong);
    }
}
