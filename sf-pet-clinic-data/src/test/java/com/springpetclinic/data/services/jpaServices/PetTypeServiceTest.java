package com.springpetclinic.data.services.jpaServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.repositories.PetTypeRepo;
import com.springpetclinic.data.services.datajpaBased.PetTypeJPAService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetTypeServiceTest {

    private PetType petType;

    @Mock
    private PetTypeRepo petTypeRepo;

    @InjectMocks
    private PetTypeJPAService petTypeJpaService;

    @BeforeEach
    public void setUp() {
        petType = PetType.builder().id(1L).name("Dog").build();
    }

    @AfterEach
    public void tearDown() {
        petType = null;
    }

    @Test
    public void findById() {
        ArgumentMatcher<Long> argumentMatcher = l -> l < 5L;
        when(petTypeRepo.findById(argThat(argumentMatcher))).thenReturn(Optional.of(petType));
        assertEquals(petType, petTypeJpaService.findById(1L));
        assertNull(petTypeJpaService.findById(10L));
        verify(petTypeRepo, times(2)).findById(anyLong());
    }

    @Test
    public void findAll() {
        when(petTypeRepo.findAll()).thenReturn(Set.of(petType));
        assertEquals(1, petTypeJpaService.findAll().size());
        assertTrue(petTypeJpaService.findAll().contains(petType));
        verify(petTypeRepo, times(2)).findAll();
    }

    @Test
    public void save() throws MyException {
        when(petTypeRepo.save(petType)).thenReturn(petType);
        assertEquals(1L, petTypeJpaService.save(petType).getId());
        verify(petTypeRepo).save(any());
    }

    @Test
    public void delete() {
        petTypeJpaService.delete(petType);
        verify(petTypeRepo).delete(any());
    }

    @Test
    public void deleteById() {
        petTypeJpaService.deleteById(1L);
        verify(petTypeRepo).deleteById(any());
    }

    @Test
    public void findByName() {
        when(petTypeRepo.findByName(anyString())).thenAnswer(invocationOnMock -> {
            String name = invocationOnMock.getArgument(0);
            return name.length() <= 5 ? petType : null;
        });

        assertEquals(petType, petTypeJpaService.findByName("dog"));
        assertNull(petTypeJpaService.findByName("aaaaaaaaaaaa"));
    }

}
