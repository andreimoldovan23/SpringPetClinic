package com.springpetclinic.web.controllers;

import com.springpetclinic.data.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class ControllerVetsIndex {

    private final VetService vetService;

    public ControllerVetsIndex(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String vetsIndex(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

}
