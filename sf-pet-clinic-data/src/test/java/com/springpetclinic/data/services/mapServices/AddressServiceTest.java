package com.springpetclinic.data.services.mapServices;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NullObject;
import com.springpetclinic.data.model.Address;
import com.springpetclinic.data.services.mapBased.AbstractMapService;
import com.springpetclinic.data.services.mapBased.AddressMapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressServiceTest {

    private Address address1, address2;
    private AbstractMapService<Address, Long> addressService;

    @BeforeEach
    public void setUp() {
        address1 = Address.builder()
                .city("LasVegas")
                .streetLine("PalmStreet")
                .build();
        address2 = Address.builder()
                .city("Moscow")
                .streetLine("Kremlin")
                .build();

        addressService = new AddressMapService();
    }

    @AfterEach
    public void tearDown() {
        address1 = null;
        address2 = null;
        addressService = null;
    }

    @Test
    public void test() throws MyException {
        address1 = addressService.save(address1);
        address2 = addressService.save(address2);

        assertEquals(2, addressService.findAll().size());
        assertEquals(address1, addressService.findById(1L));
        assertEquals(address2, addressService.findById(2L));

        addressService.delete(address2);
        assertNull(addressService.findById(2L));

        addressService.deleteById(1L);
        assertNull(addressService.findById(1L));

        assertThrows(NullObject.class, () -> addressService.save(null));
    }
}
