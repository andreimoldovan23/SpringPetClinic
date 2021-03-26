package com.springpetclinic.data.services.jpaServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.Vet;
import com.springpetclinic.data.repositories.VetRepo;
import com.springpetclinic.data.services.datajpaBased.VetJPAService;
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
public class VetServiceTest {

    private Vet vet;

    @Mock
    private VetRepo vetRepo;

    @InjectMocks
    private VetJPAService vetJPAService;

    @BeforeEach
    public void setUp() {
        vet = Vet.builder().firstName("Jack").lastName("Johnson").build();
    }

    @AfterEach
    public void tearDown() {
        vet = null;
    }

    @Test
    public void findById() {
        ArgumentMatcher<Long> argumentMatcher = l -> l < 5L;
        when(vetRepo.findById(argThat(argumentMatcher))).thenReturn(Optional.of(vet));
        assertEquals(vet, vetJPAService.findById(1L));
        assertNull(vetJPAService.findById(10L));
        verify(vetRepo, times(2)).findById(anyLong());
    }

    @Test
    public void findAll() {
        when(vetRepo.findAll()).thenReturn(Set.of(vet));
        assertEquals(1, vetJPAService.findAll().size());
        assertTrue(vetJPAService.findAll().contains(vet));
        verify(vetRepo, times(2)).findAll();
    }

    @Test
    public void save() throws MyException {
        when(vetRepo.save(vet)).thenAnswer(adr -> {
            vet.setId(1L);
            return vet;
        });

        assertEquals(1L, vetJPAService.save(vet).getId());
        verify(vetRepo).save(any());
    }

    @Test
    public void delete() {
        vetJPAService.delete(vet);
        verify(vetRepo).delete(any());
    }

    @Test
    public void deleteById() {
        vetJPAService.deleteById(1L);
        verify(vetRepo).deleteById(any());
    }
}
