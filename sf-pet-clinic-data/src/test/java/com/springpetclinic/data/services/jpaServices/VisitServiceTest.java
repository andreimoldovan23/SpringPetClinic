package com.springpetclinic.data.services.jpaServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.Visit;
import com.springpetclinic.data.repositories.VisitRepo;
import com.springpetclinic.data.services.datajpaBased.VisitJPAService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VisitServiceTest {

    private Visit visit;

    @Mock
    private VisitRepo visitRepo;

    @InjectMocks
    private VisitJPAService visitJPAService;

    @BeforeEach
    public void setUp() {
        visit = Visit.builder().description("Problem").build();
    }

    @AfterEach
    public void tearDown() {
        visit = null;
    }

    @Test
    public void findById() {
        ArgumentMatcher<Long> argumentMatcher = l -> l < 5L;
        when(visitRepo.findById(argThat(argumentMatcher))).thenReturn(Optional.of(visit));
        assertEquals(visit, visitJPAService.findById(1L));
        assertNull(visitJPAService.findById(10L));
        verify(visitRepo, times(2)).findById(anyLong());
    }

    @Test
    public void findAll() {
        when(visitRepo.findAll()).thenReturn(Set.of(visit));
        assertEquals(1, visitJPAService.findAll().size());
        assertTrue(visitJPAService.findAll().contains(visit));
        verify(visitRepo, times(2)).findAll();
    }

    @Test
    public void save() throws MyException {
        when(visitRepo.save(visit)).thenAnswer(adr -> {
            visit.setId(1L);
            return visit;
        });

        assertEquals(1L, visitJPAService.save(visit).getId());
        verify(visitRepo).save(any());
    }

    @Test
    public void delete() {
        visitJPAService.delete(visit);
        verify(visitRepo).delete(any());
    }

    @Test
    public void deleteById() {
        visitJPAService.deleteById(1L);
        verify(visitRepo).deleteById(any());
    }
}
