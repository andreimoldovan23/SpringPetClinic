package com.springpetclinic.data.services.datajpaBased;

import com.springpetclinic.data.model.VetSpecialty;
import com.springpetclinic.data.repositories.VetSpecialtyRepo;
import com.springpetclinic.data.services.VetSpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VetSpecialtyJPAService extends AbstractJPAService<VetSpecialty, Long> implements VetSpecialtyService {

    public VetSpecialtyJPAService(VetSpecialtyRepo vetSpecialtyRepo) {
        super(vetSpecialtyRepo);
    }

}
