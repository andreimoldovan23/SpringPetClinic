package com.springpetclinic.data.services.datajpaBased;

import com.springpetclinic.data.model.Vet;
import com.springpetclinic.data.repositories.VetRepo;
import com.springpetclinic.data.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VetJPAService extends AbstractJPAService<Vet, Long> implements VetService {

    public VetJPAService(VetRepo vetRepo) {
        super(vetRepo);
    }

}

