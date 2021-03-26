package com.springpetclinic.data.services.mapServices;

import com.springpetclinic.data.exceptions.*;
import com.springpetclinic.data.model.*;
import com.springpetclinic.data.services.AddressService;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.PetTypeService;
import com.springpetclinic.data.services.VisitService;
import com.springpetclinic.data.services.mapBased.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OwnerServiceTest {

    private Owner owner;
    private Address address;
    private Visit visit1, visit2;
    private PetType type1, type2;
    private Pet pet1, pet2;
    private AbstractMapService<PetType, Long> petTypeService;
    private AbstractMapService<Pet, Long> petService;
    private AbstractMapService<Visit, Long> visitService;
    private AbstractMapService<Owner, Long> ownerService;
    private AbstractMapService<Address, Long> addressService;

    @BeforeEach
    public void setUp() {
        address = Address.builder()
                .city("LasVegas")
                .streetLine("PalmStreet")
                .build();

        type1 = PetType.builder().name("cat").build();
        type2 = PetType.builder().name("dog").build();

        pet1 = Pet.builder().name("Gaston").type(type1).birthDate(LocalDate.now()).build();
        pet2 = Pet.builder().name("Rex").type(type2).birthDate(LocalDate.now()).build();

        visit1 = Visit.builder().description("Problems").localDateTime(LocalDateTime.now()).pet(pet1).build();
        visit2 = Visit.builder().description("Problems").localDateTime(LocalDateTime.now()).pet(pet2).build();

        pet1.getVisits().add(visit1);
        pet2.getVisits().add(visit2);

        owner = Owner.builder().firstName("Jack").lastName("Daniel").address(address).build();
        owner.getPets().add(pet1);
        owner.getPets().add(pet2);

        petTypeService = new PetTypeMapService();
        visitService = new VisitMapService();
        petService = new PetMapService((PetTypeService) petTypeService, (VisitService) visitService);
        addressService = new AddressMapService();
        ownerService = new OwnerMapService((PetService) petService, (AddressService) addressService);
    }

    @AfterEach
    public void tearDown() {
        type1 = null;
        type2 = null;
        pet1 = null;
        pet2 = null;
        visit1 = null;
        visit2 = null;
        address = null;
        owner = null;
        petTypeService = null;
        visitService = null;
        petService = null;
        addressService = null;
        ownerService = null;
    }

    @Test
    public void test() throws MyException {
        owner = ownerService.save(owner);

        assertEquals(2, petTypeService.findAll().size());
        assertEquals(2, visitService.findAll().size());
        assertEquals(2, petService.findAll().size());
        assertEquals(1, addressService.findAll().size());
        assertEquals(1, ownerService.findAll().size());

        assertEquals(owner, ownerService.findById(1L));

        ownerService.delete(owner);

        assertEquals(2, petTypeService.findAll().size());
        assertEquals(0, visitService.findAll().size());
        assertEquals(0, petService.findAll().size());
        assertEquals(1, addressService.findAll().size());
        assertEquals(0, ownerService.findAll().size());

        owner.setPets(new HashSet<>());
        assertThrows(NullOwner.class, () -> ownerService.save(null));
        assertThrows(OwnerWithoutPets.class, () -> ownerService.save(owner));
        owner.getPets().add(pet1);
        owner.setAddress(null);
        assertThrows(OwnerWithoutAddress.class, () -> ownerService.save(owner));
    }
}
