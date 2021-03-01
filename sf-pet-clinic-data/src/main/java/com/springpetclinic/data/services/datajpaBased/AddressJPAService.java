package com.springpetclinic.data.services.datajpaBased;

import com.springpetclinic.data.model.Address;
import com.springpetclinic.data.repositories.AddressRepo;
import com.springpetclinic.data.services.AddressService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class AddressJPAService extends AbstractJPAService<Address, Long> implements AddressService {

    public AddressJPAService(AddressRepo addressRepo) {
        super(addressRepo);
    }

}
