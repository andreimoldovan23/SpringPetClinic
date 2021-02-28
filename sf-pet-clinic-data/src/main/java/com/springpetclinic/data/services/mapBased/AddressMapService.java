package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Address;
import com.springpetclinic.data.services.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressMapService extends AbstractMapService<Address, Long> implements AddressService {
}
