package com.gridasovka.demo;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class IndexController {
    @Autowired
    ContactRepository contactRepository;

    @ModelAttribute("contacts")
    public Iterable<Contact> populateContacts() {
        return this.contactRepository.findAll();
    }
    
    @GetMapping(value="/")
    public String show(Contact contact) {
        return "index";
    }
    
    @PostMapping(value="/")
    public String handle(@Valid Contact contact, BindingResult bindingResult, final ModelMap model) {

        if (!bindingResult.hasErrors()) {
            contactRepository.save(contact);
            model.clear();
            return "redirect:/";
        }

        return "index";
    }
    
    
}