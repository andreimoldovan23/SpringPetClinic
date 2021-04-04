package com.springpetclinic.web.controllers;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class ControllerOwners {

    private final OwnerService ownerService;

    public ControllerOwners(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{id}")
    public ModelAndView showOwner(@PathVariable String id) {
        Owner owner = ownerService.findById(Long.valueOf(id));
        ModelAndView mav;
        if(owner == null) {
            mav = new ModelAndView("error/index");
        } else {
            mav = new ModelAndView("owners/details");
            mav.addObject(owner);
        }
        return mav;
    }

    @GetMapping("/find")
    public String initFindForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/find";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        if(owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> owners = ownerService.findAllByLastNameLike(owner.getLastName());
        switch (owners.size()) {
            case 0 -> {
                bindingResult.rejectValue("lastName", "not found", "not found");
                return "owners/find";
            }
            case 1 -> {
                return "redirect:/owners/" + owners.get(0).getId();
            }
            default -> {
                model.addAttribute("selections", owners);
                return "owners/list";
            }
        }
    }

    @GetMapping("/new")
    public String initCreate(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateForm";
    }

    @PostMapping("/new")
    public String processCreate(@Validated Owner owner, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "owners/createOrUpdateForm";
        }

        Owner savedOwner;
        try {
            savedOwner = ownerService.save(owner);
        } catch (MyException e) {
            return "redirect:/oups";
        }
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{id}/edit")
    public String initUpdate(@PathVariable String id, Model model) {
        Owner owner = ownerService.findById(Long.valueOf(id));
        if(owner == null) {
            return "redirect:/oups";
        }
        model.addAttribute("owner", owner);
        return "owners/createOrUpdateForm";
    }

    @PostMapping("/{id}/edit")
    public String processUpdate(@Validated Owner owner, BindingResult bindingResult, @PathVariable String id) {
        if(bindingResult.hasErrors()) {
            return "owners/createOrUpdateForm";
        }

        owner.setId(Long.valueOf(id));
        try {
            ownerService.save(owner);
        } catch (MyException e) {
            return "redirect:/oups";
        }
        return "redirect:/owners/" + owner.getId();
    }

}
