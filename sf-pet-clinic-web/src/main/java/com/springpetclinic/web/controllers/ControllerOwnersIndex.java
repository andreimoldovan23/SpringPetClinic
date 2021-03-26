package com.springpetclinic.web.controllers;

import com.springpetclinic.data.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class ControllerOwnersIndex {

    private final OwnerService ownerService;

    public ControllerOwnersIndex(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html", "/find"})
    public String ownersIndex(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

}
