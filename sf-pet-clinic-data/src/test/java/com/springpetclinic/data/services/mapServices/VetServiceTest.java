package com.springpetclinic.data.services.mapServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullSpecialty;
import com.springpetclinic.data.exceptions.NullVet;
import com.springpetclinic.data.exceptions.VetWithoutSpecialties;
import com.springpetclinic.data.model.Vet;
import com.springpetclinic.data.model.VetSpecialty;
import com.springpetclinic.data.services.VetSpecialtyService;
import com.springpetclinic.data.services.mapBased.AbstractMapService;
import com.springpetclinic.data.services.mapBased.VetMapService;
import com.springpetclinic.data.services.mapBased.VetSpecialtyMapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class VetServiceTest {

    private VetSpecialty vetSpecialty1, vetSpecialty2;
    private Vet vet1, vet2;
    private AbstractMapService<Vet, Long> vetService;
    private AbstractMapService<VetSpecialty, Long> vetSpecialtyService;

    @BeforeEach
    public void setUp() {
        vetSpecialty1 = VetSpecialty.builder().specialtyName("surgery").build();
        vetSpecialty2 = VetSpecialty.builder().specialtyName("radiology").build();

        vet1 = Vet.builder().firstName("Jack").lastName("Michael").build();
        vet2 = Vet.builder().firstName("George").lastName("Tyson").build();
        vet2.getSpecialties().add(vetSpecialty1);

        vetSpecialtyService = new VetSpecialtyMapService();
        vetService = new VetMapService((VetSpecialtyService) vetSpecialtyService);
    }

    @AfterEach
    public void tearDown() {
        vetSpecialty1 = null;
        vetSpecialty2 = null;
        vet1 = null;
        vet2 = null;
        vetSpecialtyService = null;
        vetService = null;
    }

    @Test
    public void test() throws MyException {
        assertThrows(NullVet.class, () -> vetService.save(null));
        assertThrows(VetWithoutSpecialties.class, () -> vetService.save(vet1));
        vet1.getSpecialties().add(null);
        assertThrows(NullSpecialty.class, () -> vetService.save(vet1));

        vet1.setSpecialties(new HashSet<>());
        vet1.getSpecialties().add(vetSpecialty1);
        vet1.getSpecialties().add(vetSpecialty2);

        vet1 = vetService.save(vet1);
        vet2 = vetService.save(vet2);

        vetSpecialty1.setId(1L);
        vetSpecialty2.setId(2L);

        assertTrue(vetSpecialtyService.findAll().contains(vetSpecialty1));
        assertTrue(vetSpecialtyService.findAll().contains(vetSpecialty2));
        assertEquals(2, vetService.findAll().size());
        assertEquals(vet1, vetService.findById(1L));
        assertEquals(vet2, vetService.findById(2L));
        assertEquals(2, vetSpecialtyService.findAll().size());

        vetService.delete(vet1);
        vetService.deleteById(2L);

        assertEquals(0, vetService.findAll().size());
    }
}
