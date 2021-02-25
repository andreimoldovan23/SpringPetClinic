package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.services.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    public Owner findByLastName(String lastName) {
        return null;
    }

}
