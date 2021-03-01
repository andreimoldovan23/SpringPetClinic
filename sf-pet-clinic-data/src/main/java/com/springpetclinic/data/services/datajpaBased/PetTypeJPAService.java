package com.springpetclinic.data.services.datajpaBased;

import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.repositories.PetTypeRepo;
import com.springpetclinic.data.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetTypeJPAService extends AbstractJPAService<PetType, Long> implements PetTypeService {

    public PetTypeJPAService(PetTypeRepo petTypeRepo) {
        super(petTypeRepo);
    }

}
