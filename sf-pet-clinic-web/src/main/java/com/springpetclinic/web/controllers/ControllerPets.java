package com.springpetclinic.web.controllers;

import com.springpetclinic.data.exceptions.MyException;
import com.springpetclinic.data.model.Owner;
import com.springpetclinic.data.model.Pet;
import com.springpetclinic.data.model.PetType;
import com.springpetclinic.data.services.OwnerService;
import com.springpetclinic.data.services.PetService;
import com.springpetclinic.data.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class ControllerPets {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateFormPet";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public ControllerPets(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        return redirectErrorOrAddPet(pet, result, owner, model);
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        if(pet == null) return "redirect:/error";
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner, Model model) {
        return redirectErrorOrAddPet(pet, result, owner, model);
    }

    private String redirectErrorOrAddPet(Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            try {
                pet = petService.save(pet);
                owner.getPets().add(pet);
            } catch (MyException e) {
                return "redirect:/error";
            }
            return "redirect:/owners/" + owner.getId();
        }
    }

}
