package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.exceptions.*;
import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.services.OwnerService;
import com.springpetclinic.data.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;

    public OwnerMapService(PetService petService) {
        this.petService = petService;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public Owner save(Owner owner) throws MyException {
        if(owner == null)
            throw new NullOwner();

        if(owner.getId() == null) {
            Long id = super.id.incrementAndGet();
            owner.setId(id);
        }

        Set<Pet> pets = owner.getPets();
        Set<Pet> newPets = new HashSet<>();
        for(var pet : pets) {
            if (pet == null)
                throw new NullPet();
            pet.setOwner(owner);
            newPets.add(petService.save(pet));
        }
        owner.setPets(newPets);

        super.map.put(owner.getId(), owner);
        return owner;
    }

    private void deleteCascade(Owner owner) {
        List<Pet> pets = petService.findAll().stream()
                .filter(pet -> owner.getPets().contains(pet))
                .collect(Collectors.toList());
        pets.forEach(petService::delete);
    }

    @Override
    public void delete(Owner owner) {
        deleteCascade(owner);
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        Owner type = findById(id);
        deleteCascade(type);
        super.deleteById(id);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return super.map.values().stream()
                .filter(owner -> owner.getLastName().contains(lastName))
                .collect(Collectors.toList());
    }

}
