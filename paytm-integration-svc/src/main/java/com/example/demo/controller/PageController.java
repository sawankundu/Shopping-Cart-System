package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class PageController {
	
	@RequestMapping("/address")
	public String homePage() {
		return "home";
	}
	
	@RequestMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
}
