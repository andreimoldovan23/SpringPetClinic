package com.springpetclinic.data.services.mapBased;

import com.springpetclinic.data.model.Vet;
import com.springpetclinic.data.services.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

}
