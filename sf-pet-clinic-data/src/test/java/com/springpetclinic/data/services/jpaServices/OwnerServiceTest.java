package com.springpetclinic.data.services.jpaServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.repositories.OwnerRepo;
import com.springpetclinic.data.services.datajpaBased.OwnerJPAService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    private static final String LAST_NAME = "Smith";

    @Mock
    private OwnerRepo ownerRepository;

    @InjectMocks
    private OwnerJPAService service;

    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(2L).lastName(LAST_NAME).build();
    }

    @AfterEach
    public void tearDown() {
        returnOwner = null;
    }

    @Test
    public void findByLastName() {
        ArgumentMatcher<String> lastName = s -> s.contains(LAST_NAME);
        when(ownerRepository.findAllByLastNameLike(argThat(lastName))).thenReturn(List.of(returnOwner, Owner.builder().build()));
        List<Owner> owners = service.findAllByLastNameLike(LAST_NAME);
        assertEquals(2, owners.size());
        assertTrue(owners.contains(returnOwner));
        verify(ownerRepository).findAllByLastNameLike(anyString());
    }

    @Test
    public void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();
        Owner newOwner = Owner.builder().id(3L).firstName(LAST_NAME).build();
        returnOwnersSet.add(returnOwner);
        returnOwnersSet.add(newOwner);
        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);
        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
        assertTrue(owners.contains(returnOwner));
        assertTrue(owners.contains(newOwner));
    }

    @Test
    public void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = service.findById(2L);
        assertNotNull(owner);
    }

    @Test
    public void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = service.findById(2L);
        assertNull(owner);
    }


    @Test
    public void save() throws MyException {
        Owner ownerToSave = Owner.builder().build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    public void delete() {
        service.delete(returnOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    public void deleteById() {
        service.deleteById(2L);
        verify(ownerRepository).deleteById(anyLong());
    }
}
