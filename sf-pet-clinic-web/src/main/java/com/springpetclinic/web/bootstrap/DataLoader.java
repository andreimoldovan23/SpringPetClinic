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
    private final VisitService visitService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, VisitService visitService,
                      PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.visitService = visitService;
        this.petTypeService = petTypeService;
    }

    private <T, ID> void printEntities(CrudService<T, ID> service) {
        service.findAll().forEach(System.out::println);
        System.out.println("#########################");
        System.out.println("#########################");
    }

    private void loadData() throws MyException {
        PetType dogType = PetType.builder().name("dog").build();
        PetType catType = PetType.builder().name("cat").build();
        petTypeService.save(dogType);
        petTypeService.save(catType);

        VetSpecialty radiology = VetSpecialty.builder().specialtyName("radiology").build();
        VetSpecialty surgery = VetSpecialty.builder().specialtyName("surgery").build();
        VetSpecialty dentistry = VetSpecialty.builder().specialtyName("dentistry").build();

        Owner owner1 = Owner.builder().firstName("Michael").lastName("Weston")
                .phoneNumber("0756823468").city("Cluj").streetLine("Wolfgang Goethe 20").build();
        Owner owner2 = Owner.builder().firstName("Fiona").lastName("Glenn")
                .phoneNumber("0230251122").city("Suceava").streetLine("Narciselor 4").build();
        Owner owner3 = Owner.builder().firstName("Jack").lastName("Tyson")
                .phoneNumber("04562345123").city("Vienna").streetLine("Mozart 45").build();

        Pet pet1 = Pet.builder().type(catType).birthDate(LocalDate.now())
                .name("Gaston").owner(owner1).build();
        Pet pet2 = Pet.builder().type(dogType).birthDate(LocalDate.now())
                .name("Rosco").owner(owner2).build();
        Pet pet3 = Pet.builder().type(catType).birthDate(LocalDate.now())
                .name("Felix").owner(owner1).build();
        Pet pet4 = Pet.builder().type(dogType).birthDate(LocalDate.now())
                .name("Rex").owner(owner3).build();

        Visit catVisit = Visit.builder().date(LocalDateTime.now())
                .description("Sneezy kitty").pet(pet1).build();
        Visit dogVisit = Visit.builder().date(LocalDateTime.now())
                .description("Digestion problems").pet(pet2).build();
        Visit dog2Visit = Visit.builder().date(LocalDateTime.now())
                .description("Broken nose").pet(pet2).build();
        pet1.getVisits().add(catVisit);
        pet2.getVisits().add(dogVisit);
        pet2.getVisits().add(dog2Visit);

        owner1.getPets().add(pet1);
        owner1.getPets().add(pet3);
        owner2.getPets().add(pet2);
        owner3.getPets().add(pet4);
        ownerService.save(owner1);
        ownerService.save(owner2);
        ownerService.save(owner3);
        System.out.println("Loaded owners, pet types, pets, visits....");

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
