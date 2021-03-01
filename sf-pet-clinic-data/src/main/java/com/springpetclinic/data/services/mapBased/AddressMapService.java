package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Address;
import com.springpetclinic.data.services.AddressService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class AddressMapService extends AbstractMapService<Address, Long> implements AddressService {
}
