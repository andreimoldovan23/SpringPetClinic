package com.springpetclinic.web.controllers;

import com.springpetclinic.data.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/vets", "/vets.html"})
public class ControllerVets {

    private final VetService vetService;

    public ControllerVets(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String vetsIndex(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

}
