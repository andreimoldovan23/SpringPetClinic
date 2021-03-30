package com.springpetclinic.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerIndex {

    @GetMapping({"", "/", "index", "index.html"})
    public String index() {
        return "index";
    }

    @GetMapping({"/error", "/oups"})
    public String errorHandler() {
        return "error/index";
    }

}
