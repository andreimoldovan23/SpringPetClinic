package com.springpetclinic.web.controllers;

import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.services.OwnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ControllerOwnersTest {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private ControllerOwners controllerOwners;

    MockMvc mockMvc;

    private Owner owner1, owner2;

    @BeforeEach
    public void setUp() {
        owner1 = Owner.builder().id(1L).build();
        owner2 = Owner.builder().id(2L).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controllerOwners).build();
    }

    @AfterEach
    public void tearDown() {
        owner1 = null;
        owner2 = null;
        mockMvc = null;
    }

    @Test
    public void testGetOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenAnswer(invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            return id <= 5L ? owner1 : null;
        });

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/details"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));

        mockMvc.perform(get("/owners/7"))
                .andExpect(status().isOk())
                .andExpect(view().name("error/index"));
    }

    @Test
    public void testFindForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    public void testProcessFindFormOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of(owner1));
        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

    }

    @Test
    public void testProcessFindFormMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of(owner1, owner2));
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/list"))
                .andExpect(model().attributeExists("selections"));
    }

    @Test
    public void testProcessFindFormNone() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of());
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find"));
    }

    @Test
    public void testCreateForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateForm"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    public void testCreateFormProcess() throws Exception {
        when(ownerService.save(any())).thenReturn(owner1);
        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + owner1.getId()));
    }

    @Test
    public void testUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateForm"))
                .andExpect(model().attribute("owner", hasProperty("id", is(owner1.getId()))));
    }

    @Test
    public void testUpdateFormInvalid() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(null);
        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/oups"));
    }

    @Test
    public void testUpdateFormProcess() throws Exception {
        when(ownerService.save(any())).thenReturn(owner1);
        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + owner1.getId()))
                .andExpect(model().attribute("owner", hasProperty("id", is(owner1.getId()))));
    }

}
