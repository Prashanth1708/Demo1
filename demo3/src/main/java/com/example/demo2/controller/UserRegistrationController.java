package com.example.demo2.controller;

import com.example.demo2.service.UserService;
import com.example.demo2.web.dto.UserRegistrationDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userservice;

    public UserRegistrationController(UserService userservice) {
        super();
        this.userservice = userservice;
    }
    

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){        
        return new UserRegistrationDto();
    }
    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto){
        userservice.save(registrationDto);
        return "redirect:/registration?success";
    }
    
    
}