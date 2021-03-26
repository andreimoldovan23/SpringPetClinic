package com.springpetclinic.data.services.jpaServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.VetSpecialty;
import com.springpetclinic.data.repositories.VetSpecialtyRepo;
import com.springpetclinic.data.services.datajpaBased.VetSpecialtyJPAService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VetSpecialtyServiceTest {

    private VetSpecialty vetSpecialty;

    @Mock
    private VetSpecialtyRepo vetRepo;

    @InjectMocks
    private VetSpecialtyJPAService vetJPAService;

    @BeforeEach
    public void setUp() {
        vetSpecialty = VetSpecialty.builder().specialtyName("surgery").build();
    }

    @AfterEach
    public void tearDown() {
        vetSpecialty = null;
    }

    @Test
    public void findById() {
        ArgumentMatcher<Long> argumentMatcher = l -> l < 5L;
        when(vetRepo.findById(argThat(argumentMatcher))).thenReturn(Optional.of(vetSpecialty));
        assertEquals(vetSpecialty, vetJPAService.findById(1L));
        assertNull(vetJPAService.findById(10L));
        verify(vetRepo, times(2)).findById(anyLong());
    }

    @Test
    public void findAll() {
        when(vetRepo.findAll()).thenReturn(Set.of(vetSpecialty));
        assertEquals(1, vetJPAService.findAll().size());
        assertTrue(vetJPAService.findAll().contains(vetSpecialty));
        verify(vetRepo, times(2)).findAll();
    }

    @Test
    public void save() throws MyException {
        when(vetRepo.save(vetSpecialty)).thenAnswer(adr -> {
            vetSpecialty.setId(1L);
            return vetSpecialty;
        });

        assertEquals(1L, vetJPAService.save(vetSpecialty).getId());
        verify(vetRepo).save(any());
    }

    @Test
    public void delete() {
        vetJPAService.delete(vetSpecialty);
        verify(vetRepo).delete(any());
    }

    @Test
    public void deleteById() {
        vetJPAService.deleteById(1L);
        verify(vetRepo).deleteById(any());
    }
}
