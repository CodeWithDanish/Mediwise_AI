package com.health.mediwiseai.controller;

import com.health.mediwiseai.model.User;
import com.health.mediwiseai.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/signup")
    public String signup(@RequestParam String firstName, @RequestParam String lastName,
    		@RequestParam String username, @RequestParam String password,
    		@RequestParam String email, @RequestParam String role,
                         RedirectAttributes redirectAttributes) {

        if (userRepository.existsByUsername(username)) {
            redirectAttributes.addFlashAttribute("error", "Username already exists!");
            return "redirect:/signup";
        }

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        
        newUser.setRole(role);
         // In real apps, hash this
        userRepository.save(newUser);

        return "redirect:/login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                         HttpSession session, RedirectAttributes redirectAttributes) {
    	
    	Optional<User> userOpt = userRepository.findByUsername(username);
    	
    	if(userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
    		session.setAttribute("loggedInUser", userOpt.get());
    		return "dashboard";
    	}

    	redirectAttributes.addFlashAttribute("error", "Invalid Username or Password !");

        return "redirect:/login";
    }


   
}

 

