package com.springpetclinic.data.services.mapServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullVisit;
import com.springpetclinic.data.exceptions.VisitWithoutPet;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.Visit;
import com.springpetclinic.data.services.mapBased.AbstractMapService;
import com.springpetclinic.data.services.mapBased.VisitMapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VisitServiceTest {

    private Visit visit1, visit2;
    private AbstractMapService<Visit, Long> visitService;

    @BeforeEach
    public void setUp() {
        visit1 = Visit.builder()
                .description("Problem")
                .localDateTime(LocalDateTime.now())
                .build();
        visit2 = Visit.builder()
                .description("Problem")
                .localDateTime(LocalDateTime.now())
                .build();
        visit1.setPet(Pet.builder().build());
        visit2.setPet(Pet.builder().build());

        visitService = new VisitMapService();
    }

    @AfterEach
    public void tearDown() {
        visit1 = null;
        visit2 = null;
        visitService = null;
    }

    @Test
    public void test() throws MyException {
        visit1 = visitService.save(visit1);
        visit2 = visitService.save(visit2);

        assertEquals(2, visitService.findAll().size());
        assertEquals(visit1, visitService.findById(1L));
        assertEquals(visit2, visitService.findById(2L));

        visitService.delete(visit2);
        assertNull(visitService.findById(2L));

        visitService.deleteById(1L);
        assertNull(visitService.findById(1L));

        visit1.setPet(null);
        assertThrows(NullVisit.class, () -> visitService.save(null));
        assertThrows(VisitWithoutPet.class, () -> visitService.save(visit1));
    }
}
