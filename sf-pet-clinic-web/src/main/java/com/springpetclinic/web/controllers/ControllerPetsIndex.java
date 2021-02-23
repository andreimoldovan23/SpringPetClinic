package com.springpetclinic.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pets")
public class ControllerPetsIndex {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String petsIndex() {
        return "pets/index";
    }

}
