package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.VetSpecialty;
import com.springpetclinic.data.services.VetSpecialtyService;
import org.springframework.stereotype.Service;

@Service
public class VetSpecialtyMapService extends AbstractMapService<VetSpecialty, Long> implements VetSpecialtyService {
}
