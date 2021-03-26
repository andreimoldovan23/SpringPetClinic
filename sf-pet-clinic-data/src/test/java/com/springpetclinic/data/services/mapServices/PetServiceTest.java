package com.springpetclinic.data.services.mapServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullPet;
import com.springpetclinic.data.exceptions.PetWithoutName;
import com.springpetclinic.data.exceptions.PetWithoutType;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.model.Visit;
import com.springpetclinic.data.services.PetTypeService;
import com.springpetclinic.data.services.VisitService;
import com.springpetclinic.data.services.mapBased.AbstractMapService;
import com.springpetclinic.data.services.mapBased.PetMapService;
import com.springpetclinic.data.services.mapBased.PetTypeMapService;
import com.springpetclinic.data.services.mapBased.VisitMapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PetServiceTest {

    private Visit visit1, visit2;
    private PetType type1, type2;
    private Pet pet1, pet2;
    private AbstractMapService<PetType, Long> petTypeService;
    private AbstractMapService<Pet, Long> petService;
    private AbstractMapService<Visit, Long> visitService;

    @BeforeEach
    public void setUp() {
        type1 = PetType.builder().name("cat").build();
        type2 = PetType.builder().name("dog").build();

        pet1 = Pet.builder().name("Gaston").type(type1).birthDate(LocalDate.now()).build();
        pet2 = Pet.builder().name("Rex").type(type2).birthDate(LocalDate.now()).build();

        visit1 = Visit.builder().description("Problems").localDateTime(LocalDateTime.now()).pet(pet1).build();
        visit2 = Visit.builder().description("Problems").localDateTime(LocalDateTime.now()).pet(pet2).build();

        pet1.getVisits().add(visit1);
        pet2.getVisits().add(visit2);

        petTypeService = new PetTypeMapService();
        visitService = new VisitMapService();
        petService = new PetMapService((PetTypeService) petTypeService, (VisitService) visitService);
    }

    @AfterEach
    public void tearDown() {
        type1 = null;
        type2 = null;
        pet1 = null;
        pet2 = null;
        visit1 = null;
        visit2 = null;
        petTypeService = null;
        visitService = null;
        petService = null;
    }

    @Test
    public void test() throws MyException {
        pet1 = petService.save(pet1);
        pet2 = petService.save(pet2);

        assertEquals(2, petTypeService.findAll().size());
        assertEquals(2, visitService.findAll().size());
        assertEquals(2, petService.findAll().size());

        assertEquals(pet1, petService.findById(1L));
        assertEquals(pet2, petService.findById(2L));

        petService.delete(pet1);
        petService.deleteById(2L);

        assertEquals(2, petTypeService.findAll().size());
        assertEquals(0, visitService.findAll().size());
        assertEquals(0, petService.findAll().size());

        pet1.setName(null);
        pet2.setType(null);
        assertThrows(NullPet.class, () -> petService.save(null));
        assertThrows(PetWithoutName.class, () -> petService.save(pet1));
        assertThrows(PetWithoutType.class, () -> petService.save(pet2));
    }
}
