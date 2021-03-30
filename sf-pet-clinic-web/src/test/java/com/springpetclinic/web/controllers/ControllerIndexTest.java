package com.springpetclinic.web.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class ControllerIndexTest {
    private MockMvc mockMvc;

    private ControllerIndex controllerIndex;

    @BeforeEach
    public void setUp() {
        controllerIndex = new ControllerIndex();
        mockMvc = MockMvcBuilders.standaloneSetup(controllerIndex).build();
    }

    @AfterEach
    public void tearDown() {
        controllerIndex = null;
        mockMvc = null;
    }

    @Test
    public void indexTest() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void errorTest() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error/index"));
    }

}
