package com.springpetclinic.data.services.datajpaBased;

import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.repositories.OwnerRepo;
import com.springpetclinic.data.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdatajpa")
public class OwnerJPAService extends AbstractJPAService<Owner, Long> implements OwnerService {

    public OwnerJPAService(OwnerRepo ownerRepo) {
        super(ownerRepo);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ((OwnerRepo)super.repository).findAllByLastNameLike("%" + lastName + "%");
    }

}
