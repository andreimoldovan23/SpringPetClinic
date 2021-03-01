package com.springpetclinic.data.services.datajpaBased;

import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.repositories.PetRepo;
import com.springpetclinic.data.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetJPAService extends AbstractJPAService<Pet, Long> implements PetService {

    public PetJPAService(PetRepo petRepo) {
        super(petRepo);
    }

}
