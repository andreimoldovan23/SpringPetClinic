package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.exceptions.*;
import com.springpetclinic.data.model.Address;
import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.services.OwnerService;
import com.springpetclinic.data.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final AddressMapService addressMapService;

    public OwnerMapService(PetService petService, AddressMapService addressMapService) {
        this.petService = petService;
        this.addressMapService = addressMapService;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Owner save(Owner owner) throws MyException {
        if(owner == null)
            throw new NullOwner();
        if(owner.getPets().size() == 0)
            throw new OwnerWithoutPets();

        Set<Pet> pets = owner.getPets();
        for(var pet : pets) {
            if (pet == null)
                throw new NullPet();
            if(pet.getId() == null) {
                pet.setOwner(owner);
                petService.save(pet);
            }
        }

        Address address = owner.getAddress();
        if(address == null)
            throw new OwnerWithoutAddress();
        if(address.getId() == null)
            addressMapService.save(address);

        if(owner.getId() == null) {
            Long id = super.id.incrementAndGet();
            owner.setId(id);
        }

        super.map.put(owner.getId(), owner);
        return owner;
    }

    public Owner findByLastName(String lastName) {
        return null;
    }

}
