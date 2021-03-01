package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.exceptions.*;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

    private final PetTypeService petTypeService;

    public PetMapService(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
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
        if(petType.getId() == null)
            petTypeService.save(petType);

        if(pet.getId() == null) {
            Long id = super.id.incrementAndGet();
            pet.setId(id);
        }

        super.map.put(pet.getId(), pet);
        return pet;
    }

}
