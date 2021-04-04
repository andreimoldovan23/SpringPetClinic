package com.springpetclinic.web.controllers;

import com.springpetclinic.data.exceptions.NullVisit;
import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.Visit;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.VisitService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ControllerVisitsTest {

    @Mock
    private PetService petService;

    @Mock
    private VisitService visitService;

    @InjectMocks
    private ControllerVisit controllerVisit;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controllerVisit).build();
    }

    @AfterEach
    public void tearDown() {
        mockMvc = null;
    }

    @Test
    public void testInitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateFormVisit"))
                .andExpect(model().attribute("pet", hasProperty("id", is(1L))))
                .andExpect(model().attributeExists("visit"));
    }

    @Test
    public void testInitFormWrong() throws Exception {
        when(petService.findById(anyLong())).thenReturn(null);
        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/oups"));
    }

    @Test
    public void testProcessForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L)
                .owner(Owner.builder().id(1L).build())
                .build());
        when(visitService.save(any())).thenReturn(Visit.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    public void testProcessFormWrong() throws Exception {
        when(visitService.save(any())).thenThrow(NullVisit.class);

        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/oups"));
    }
}
