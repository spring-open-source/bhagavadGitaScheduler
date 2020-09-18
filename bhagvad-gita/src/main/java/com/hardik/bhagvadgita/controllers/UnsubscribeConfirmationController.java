package com.hardik.bhagvadgita.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UnsubscribeConfirmationController {
	
	@GetMapping("/unsubscribe/{email}")
	public String unsubscribeConfirmationHandler(@PathVariable("email") String email, Model model) {
		model.addAttribute("email", email);
		return "unsubscribe-confirmation";
	}

}
