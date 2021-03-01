package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

}
