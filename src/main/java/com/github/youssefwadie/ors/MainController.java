package com.github.youssefwadie.ors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	@GetMapping("")
	public String getReservationForm() {
		return "redirect:/reservation";
	}
}
