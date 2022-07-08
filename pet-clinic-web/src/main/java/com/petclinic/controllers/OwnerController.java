package com.petclinic.controllers;

import com.petclinic.model.Owner;
import com.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

//    @GetMapping({"", "/" })
//    public String ownerList(Model model){
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/ownerPage";
//    }

    @GetMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "/owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model){

        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if(results.isEmpty()) {
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }else if(results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }else {
            model.addAttribute("listOwners", results);
            return "owners/ownersList";
        }
    }

    @GetMapping({"/{ownerId}"})
    public ModelAndView showOwner(@PathVariable Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result){
        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId){
        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }


}
