package com.petclinic.controllers;

import com.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String ownerList(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "ownerPage";
    }

    @RequestMapping("/find")
    public String findOwners(){

        return "notImplementedPage";
    }


}
