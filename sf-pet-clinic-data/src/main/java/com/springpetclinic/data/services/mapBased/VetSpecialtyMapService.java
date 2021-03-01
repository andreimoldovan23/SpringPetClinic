package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.VetSpecialty;
import com.springpetclinic.data.services.VetSpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class VetSpecialtyMapService extends AbstractMapService<VetSpecialty, Long> implements VetSpecialtyService {
}
