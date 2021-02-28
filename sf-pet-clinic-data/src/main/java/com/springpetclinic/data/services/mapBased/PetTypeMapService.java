package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.services.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

}
