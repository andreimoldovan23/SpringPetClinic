package com.springpetclinic.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class ControllerOwnersIndex {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String ownersIndex() {
        return "owners/index";
    }

}
