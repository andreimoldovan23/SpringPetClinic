package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.services.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

}
