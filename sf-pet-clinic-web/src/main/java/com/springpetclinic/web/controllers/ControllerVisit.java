package com.springpetclinic.web.controllers;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.Visit;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/owners/*/pets/{petId}")
public class ControllerVisit {

    private final PetService petService;
    private final VisitService visitService;

    public ControllerVisit(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                System.out.println(text);
                setValue(LocalDateTime.parse(text));
            }
        });
    }

    @GetMapping("/visits/new")
    public String initVisitForm(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        if(pet == null) return "redirect:/oups";

        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        model.addAttribute("visit", visit);
        return "pets/createOrUpdateFormVisit";
    }

    @PostMapping("/visits/new")
    public String processVisitForm(@PathVariable Long petId, @Validated Visit visit, BindingResult result) {
        if(result.hasErrors()) {
            return "pets/createOrUpdateFormVisit";
        }
        try {
            Pet pet = petService.findById(petId);
            visit.setPet(pet);
            visitService.save(visit);
            return "redirect:/owners/" + pet.getOwner().getId();
        } catch (MyException mex) {
            return "redirect:/oups";
        }
    }

}
