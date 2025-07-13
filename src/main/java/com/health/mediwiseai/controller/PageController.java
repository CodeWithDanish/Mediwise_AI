package com.health.mediwiseai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.health.mediwiseai.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
	
	@GetMapping("/signup")
	String signupPage() {
		return "signup";
	}
	
	@GetMapping("/login")
	String loginPage() {
		return "login";
	}
	
	   @GetMapping("/dashboard")
	    String cardDashboard(HttpSession session, Model model) {
	    	User user = (User) session.getAttribute("loggedInUser");
	    	if (user == null) {
				return "redirect:/login";
			}
	    	model.addAttribute("user", user);
	    		return "dashboard";
	  }
	   @GetMapping("/logout")
	    String logout(HttpSession session) {
	    	session.invalidate();
	    	return "redirect:/login";
	  }
}
