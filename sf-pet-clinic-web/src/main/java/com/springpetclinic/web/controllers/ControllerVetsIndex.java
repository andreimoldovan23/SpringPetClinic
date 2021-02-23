package com.springpetclinic.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class ControllerVetsIndex {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String vetsIndex() {
        return "vets/index";
    }

}
