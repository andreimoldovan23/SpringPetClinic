package com.springpetclinic.data.services.mapServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullObject;
import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.services.mapBased.AbstractMapService;
import com.springpetclinic.data.services.mapBased.PetTypeMapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTypeServiceTest {

    private PetType petType1, petType2;
    private PetTypeMapService petTypeService;

    @BeforeEach
    public void setUp() {
        petType1 = PetType.builder()
                .name("Dog")
                .build();
        petType2 = PetType.builder()
                .name("Cat")
                .build();

        petTypeService = new PetTypeMapService();
    }

    @AfterEach
    public void tearDown() {
        petType1 = null;
        petType2 = null;
        petTypeService = null;
    }

    @Test
    public void test() throws MyException {
        petType1 = petTypeService.save(petType1);
        petType2 = petTypeService.save(petType2);

        assertEquals(petType1, petTypeService.findByName("Dog"));
        assertNull(petTypeService.findByName("eagle"));

        assertEquals(2, petTypeService.findAll().size());
        assertEquals(petType1, petTypeService.findById(1L));
        assertEquals(petType2, petTypeService.findById(2L));

        petTypeService.delete(petType2);
        assertNull(petTypeService.findById(2L));

        petTypeService.deleteById(1L);
        assertNull(petTypeService.findById(1L));

        assertThrows(NullObject.class, () -> petTypeService.save(null));
    }

}
