package com.springpetclinic.data.services.datajpaBased;

import com.springpetclinic.data.model.Visit;
import com.springpetclinic.data.repositories.VisitRepo;
import com.springpetclinic.data.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VisitJPAService extends AbstractJPAService<Visit, Long> implements VisitService {

    public VisitJPAService(VisitRepo visitRepo) {
        super(visitRepo);
    }

}
