package com.springpetclinic.data.services.mapServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullObject;
import com.springpetclinic.data.model.VetSpecialty;
import com.springpetclinic.data.services.mapBased.AbstractMapService;
import com.springpetclinic.data.services.mapBased.VetSpecialtyMapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VetSpecialtyServiceTest {

    private VetSpecialty vetSpecialty1, vetSpecialty2;
    private AbstractMapService<VetSpecialty, Long> vetSpecialtyMapService;

    @BeforeEach
    public void setUp() {
        vetSpecialty1 = VetSpecialty.builder()
                .specialtyName("dentistry")
                .build();
        vetSpecialty2 = VetSpecialty.builder()
                .specialtyName("surgery")
                .build();
        vetSpecialtyMapService = new VetSpecialtyMapService();
    }

    @AfterEach
    public void tearDown() {
        vetSpecialty1 = null;
        vetSpecialty2 = null;
        vetSpecialtyMapService = null;
    }

    @Test
    public void test() throws MyException {
        vetSpecialty1 = vetSpecialtyMapService.save(vetSpecialty1);
        vetSpecialty2 = vetSpecialtyMapService.save(vetSpecialty2);

        assertEquals(2, vetSpecialtyMapService.findAll().size());
        assertEquals(vetSpecialty1, vetSpecialtyMapService.findById(1L));
        assertEquals(vetSpecialty2, vetSpecialtyMapService.findById(2L));

        vetSpecialtyMapService.delete(vetSpecialty2);
        assertNull(vetSpecialtyMapService.findById(2L));

        vetSpecialtyMapService.deleteById(1L);
        assertNull(vetSpecialtyMapService.findById(1L));

        assertThrows(NullObject.class, () -> vetSpecialtyMapService.save(null));
    }
}
