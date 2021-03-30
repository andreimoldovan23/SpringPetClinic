package com.springpetclinic.data.services.jpaServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.repositories.PetRepo;
import com.springpetclinic.data.services.datajpaBased.PetJPAService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
public class PetServiceTest {

    private Pet pet;

    @Mock
    private PetRepo petRepo;

    @InjectMocks
    private PetJPAService petJPAService;

    @BeforeEach
    public void setUp() {
        pet = Pet.builder().id(1L).name("Gaston").birthDate(LocalDate.now()).build();
    }

    @AfterEach
    public void tearDown() {
        pet = null;
    }

    @Test
    public void findById() {
        ArgumentMatcher<Long> argumentMatcher = l -> l < 5L;
        when(petRepo.findById(argThat(argumentMatcher))).thenReturn(Optional.of(pet));
        assertEquals(pet, petJPAService.findById(1L));
        assertNull(petJPAService.findById(10L));
        verify(petRepo, times(2)).findById(anyLong());
    }

    @Test
    public void findAll() {
        when(petRepo.findAll()).thenReturn(Set.of(pet));
        assertEquals(1, petJPAService.findAll().size());
        assertTrue(petJPAService.findAll().contains(pet));
        verify(petRepo, times(2)).findAll();
    }

    @Test
    public void save() throws MyException {
        when(petRepo.save(pet)).thenReturn(pet);
        assertEquals(1L, petJPAService.save(pet).getId());
        verify(petRepo).save(any());
    }

    @Test
    public void delete() {
        petJPAService.delete(pet);
        verify(petRepo).delete(any());
    }

    @Test
    public void deleteById() {
        petJPAService.deleteById(1L);
        verify(petRepo).deleteById(any());
    }
}
