package com.springpetclinic.web.bootstrap;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.*;
import com.springpetclinic.data.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private <T, ID> void printEntities(CrudService<T, ID> service) {
        service.findAll().forEach(System.out::println);
        System.out.println("#########################");
        System.out.println("#########################");
    }

    private void loadData() throws MyException {
        PetType dogType = PetType.builder().name("dog").build();
        PetType catType = PetType.builder().name("cat").build();

        VetSpecialty radiology = VetSpecialty.builder().specialtyName("radiology").build();
        VetSpecialty surgery = VetSpecialty.builder().specialtyName("surgery").build();
        VetSpecialty dentistry = VetSpecialty.builder().specialtyName("dentistry").build();

        Address address1 = Address.builder().city("Cluj").streetLine("Wolfgang Goethe 20").build();
        Address address2 = Address.builder().city("Suceava").streetLine("Narciselor 4").build();

        Owner owner1 = Owner.builder().firstName("Michael").lastName("Weston")
                .phoneNumber("0756823468").address(address1).build();
        Owner owner2 = Owner.builder().firstName("Fiona").lastName("Glenn")
                .phoneNumber("0230251122").address(address2).build();

        Pet pet1 = Pet.builder().type(catType).birthDate(LocalDate.now())
                .name("Gaston").owner(owner1).build();
        Pet pet2 = Pet.builder().type(dogType).birthDate(LocalDate.now())
                .name("Rosco").owner(owner2).build();

        Visit catVisit = Visit.builder().localDateTime(LocalDateTime.now())
                .description("Sneezy kitty").pet(pet1).build();
        pet1.getVisits().add(catVisit);

        owner1.getPets().add(pet1);
        owner2.getPets().add(pet2);
        ownerService.save(owner1);
        ownerService.save(owner2);
        System.out.println("Loaded owners, pet types, pets, visits and addresses....");

        Vet vet1 = Vet.builder().firstName("Sam").lastName("Axe").build();
        vet1.getSpecialties().add(radiology);
        vet1.getSpecialties().add(dentistry);
        Vet vet2 = Vet.builder().firstName("Jack").lastName("Porter").build();
        vet2.getSpecialties().add(surgery);

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded vets and specialties....");

        printEntities(ownerService);
        printEntities(vetService);
        printEntities(petService);
        printEntities(visitService);
    }

    public void run(String... args) throws MyException {
        if(ownerService.findAll().size() == 0)
            loadData();
    }

}
