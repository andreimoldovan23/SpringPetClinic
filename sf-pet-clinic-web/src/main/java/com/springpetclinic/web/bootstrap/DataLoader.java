package com.springpetclinic.web.bootstrap;

import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.model.Vet;
import com.springpetclinic.data.services.OwnerService;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
    }

    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Doe");

        Owner owner2 = new Owner();
        owner2.setFirstName("Jane");
        owner2.setLastName("Doe");

        Vet vet1 = new Vet();
        vet1.setFirstName("Vetting");
        vet1.setLastName("Vet");

        Vet vet2 = new Vet();
        vet2.setFirstName("Wraith");
        vet2.setLastName("Vet");

        ownerService.save(owner1);
        ownerService.save(owner2);
        System.out.println("Loaded owners");

        vetService.save(vet1);
        vetService.save(vet2);
        System.out.println("Loaded vets");
    }

}
