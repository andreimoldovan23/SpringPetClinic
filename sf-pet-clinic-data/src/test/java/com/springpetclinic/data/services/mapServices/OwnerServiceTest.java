package com.springpetclinic.data.services.mapServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullOwner;
import com.springpetclinic.data.model.*;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.VisitService;
import com.springpetclinic.data.services.mapBased.AbstractMapService;
import com.springpetclinic.data.services.mapBased.OwnerMapService;
import com.springpetclinic.data.services.mapBased.PetMapService;
import com.springpetclinic.data.services.mapBased.VisitMapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OwnerServiceTest {

    private Owner owner;
    private Visit visit1, visit2;
    private PetType type1, type2;
    private Pet pet1, pet2;
    private AbstractMapService<Pet, Long> petService;
    private AbstractMapService<Visit, Long> visitService;
    private AbstractMapService<Owner, Long> ownerService;

    @BeforeEach
    public void setUp() {
        type1 = PetType.builder().id(1L).name("cat").build();
        type2 = PetType.builder().id(2L).name("dog").build();

        pet1 = Pet.builder().name("Gaston").type(type1).birthDate(LocalDate.now()).build();
        pet2 = Pet.builder().name("Rex").type(type2).birthDate(LocalDate.now()).build();

        visit1 = Visit.builder().description("Problems").date(LocalDateTime.now()).pet(pet1).build();
        visit2 = Visit.builder().description("Problems").date(LocalDateTime.now()).pet(pet2).build();

        pet1.getVisits().add(visit1);
        pet2.getVisits().add(visit2);

        owner = Owner.builder().firstName("Jack").lastName("Daniel").build();
        owner.getPets().add(pet1);
        owner.getPets().add(pet2);

        visitService = new VisitMapService();
        petService = new PetMapService((VisitService) visitService);
        ownerService = new OwnerMapService((PetService) petService);
    }

    @AfterEach
    public void tearDown() {
        type1 = null;
        type2 = null;
        pet1 = null;
        pet2 = null;
        visit1 = null;
        visit2 = null;
        owner = null;
        visitService = null;
        petService = null;
        ownerService = null;
    }

    @Test
    public void test() throws MyException {
        owner = ownerService.save(owner);

        assertEquals(2, visitService.findAll().size());
        assertEquals(2, petService.findAll().size());
        assertEquals(1, ownerService.findAll().size());

        assertEquals(owner, ownerService.findById(1L));
        assertEquals(1, ((OwnerMapService)ownerService).findAllByLastNameLike("Dan").size());

        ownerService.delete(owner);

        assertEquals(0, visitService.findAll().size());
        assertEquals(0, petService.findAll().size());
        assertEquals(0, ownerService.findAll().size());

        owner.setPets(new HashSet<>());
        assertThrows(NullOwner.class, () -> ownerService.save(null));
    }
}
