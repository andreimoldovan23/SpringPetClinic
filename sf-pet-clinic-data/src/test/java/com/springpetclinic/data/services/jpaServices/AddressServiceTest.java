package com.springpetclinic.data.services.jpaServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.Address;
import com.springpetclinic.data.repositories.AddressRepo;
import com.springpetclinic.data.services.datajpaBased.AddressJPAService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    private Address address;

    @Mock
    private AddressRepo addressRepo;

    @InjectMocks
    private AddressJPAService addressJPAService;

    @BeforeEach
    public void setUp() {
        address = Address.builder().city("LasVegas").streetLine("45").build();
    }

    @AfterEach
    public void tearDown() {
        address = null;
    }

    @Test
    public void findById() {
        ArgumentMatcher<Long> argumentMatcher = l -> l < 5L;
        when(addressRepo.findById(argThat(argumentMatcher))).thenReturn(Optional.of(address));
        assertEquals(address, addressJPAService.findById(1L));
        assertNull(addressJPAService.findById(10L));
        verify(addressRepo, times(2)).findById(anyLong());
    }

    @Test
    public void findAll() {
        when(addressRepo.findAll()).thenReturn(Set.of(address));
        assertEquals(1, addressJPAService.findAll().size());
        assertTrue(addressJPAService.findAll().contains(address));
        verify(addressRepo, times(2)).findAll();
    }

    @Test
    public void save() throws MyException {
        when(addressRepo.save(address)).thenAnswer(adr -> {
            address.setId(1L);
            return address;
        });

        assertEquals(1L, addressJPAService.save(address).getId());
        verify(addressRepo).save(any());
    }

    @Test
    public void delete() {
        addressJPAService.delete(address);
        verify(addressRepo).delete(any());
    }

    @Test
    public void deleteById() {
        addressJPAService.deleteById(1L);
        verify(addressRepo).deleteById(any());
    }

}
