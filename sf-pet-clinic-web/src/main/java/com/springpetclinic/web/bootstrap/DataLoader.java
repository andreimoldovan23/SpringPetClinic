package com.springpetclinic.web.bootstrap;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.*;
import com.springpetclinic.data.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final VetSpecialtyService vetSpecialtyService;
    private final AddressService addressService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService,
                      PetTypeService petTypeService, VetSpecialtyService vetSpecialtyService,
                      AddressService addressService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.vetSpecialtyService = vetSpecialtyService;
        this.addressService = addressService;
        this.visitService = visitService;
    }

    public void run(String... args) {

    }

}
