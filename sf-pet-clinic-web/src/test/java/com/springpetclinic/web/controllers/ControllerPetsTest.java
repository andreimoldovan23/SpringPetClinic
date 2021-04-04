package com.springpetclinic.web.controllers;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.exceptions.NonExistentType;
import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.services.OwnerService;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.PetTypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ControllerPetsTest {

    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetTypeService petTypeService;

    @InjectMocks
    private ControllerPets controllerPets;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controllerPets).build();
        Owner owner = Owner.builder().id(1L).build();
        Set<PetType> petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
    }

    @AfterEach
    public void tearDown() {
        mockMvc = null;
    }

    @Test
    public void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdateFormPet"));
    }

    @Test
    public void processCreationForm() throws Exception {
        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    public void initUpdateForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());
        mockMvc.perform(get("/owners/1/pets/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdateFormPet"));
    }

    @Test
    public void processUpdateForm() throws Exception {
        mockMvc.perform(post("/owners/1/pets/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    public void processFormError() throws Exception {
        when(petService.save(any())).thenThrow(NonExistentType.class);
        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/oups"));
    }

}
