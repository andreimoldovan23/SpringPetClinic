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
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.visitService = visitService;
    }

    private void loadData() {
        try{
            //pet types init
            //-------------
            //-------------
            PetType dogType = new PetType();
            dogType.setName("dog");

            PetType catType = new PetType();
            catType.setName("cat");

            //pets init
            //-------------
            //-------------
            Pet pet1 = new Pet();
            pet1.setType(dogType);
            pet1.setName("Rosco");
            pet1.setBirthDate(LocalDate.now());

            Pet pet2 = new Pet();
            pet2.setType(catType);
            pet2.setName("Gaston");
            pet2.setBirthDate(LocalDate.now());

            Pet pet3 = new Pet();
            pet3.setType(catType);
            pet3.setName("Felix");
            pet3.setBirthDate(LocalDate.now());

            //address init
            //-------------
            //-------------
            Address address1 = new Address();
            address1.setStreetLine("Wolfgang Goethe 20");
            address1.setCity("Cluj");

            Address address2 = new Address();
            address2.setStreetLine("Humorului 155A");
            address2.setCity("Suceava");

            //specialties init
            //-------------
            //-------------
            VetSpecialty specialty1 = new VetSpecialty();
            specialty1.setSpecialtyName("dentistry");

            VetSpecialty specialty2 = new VetSpecialty();
            specialty2.setSpecialtyName("surgery");

            //owners init
            //-------------
            //-------------
            Owner owner1 = new Owner();
            owner1.setFirstName("Andrei");
            owner1.setLastName("Moldovan");
            owner1.setPhoneNumber("0756823468");
            owner1.setAddress(address1);
            owner1.setPets(Set.of(pet2, pet3));

            Owner owner2 = new Owner();
            owner2.setFirstName("Mihai");
            owner2.setLastName("Petrescu");
            owner2.setPhoneNumber("047265432113");
            owner2.setAddress(address2);
            owner2.setPets(Set.of(pet1));

            //vets init
            //-------------
            //-------------
            Vet vet1 = new Vet();
            vet1.setFirstName("Mike Johnson");
            vet1.setLastName("Vet");
            vet1.setSpecialties(Set.of(specialty1, specialty2));

            Vet vet2 = new Vet();
            vet2.setFirstName("Abdul");
            vet2.setLastName("Owuewuewue");
            vet2.setSpecialties(Set.of(specialty1));

            //visits init
            //-------------
            //-------------
            Visit visit1 = new Visit();
            visit1.setDescription("oral hygiene");
            visit1.setPet(pet3);

            Visit visit2 = new Visit();
            visit2.setDescription("hair dressing");
            visit2.setPet(pet2);

            //save owners
            //-------------
            //-------------
            ownerService.save(owner1);
            ownerService.save(owner2);
            System.out.println("Loaded owners");

            //save vets
            //-------------
            //-------------
            vetService.save(vet1);
            vetService.save(vet2);
            System.out.println("Loaded vets");

            //save visits
            //-------------
            //-------------
            visitService.save(visit1);
            visitService.save(visit2);

            ownerService.findAll().forEach(System.out::println);
            vetService.findAll().forEach(System.out::println);
            visitService.findAll().forEach(System.out::println);
        }
        catch (MyException me) {
            System.out.println(me);
        }
    }

    public void run(String... args) {
        if(ownerService.findAll().size() == 0)
            loadData();
    }

}
