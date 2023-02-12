package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class WelcomePage {

	@GetMapping("/welcome")
	public String welcome() {
		return "Hello";
	}
	
	@GetMapping("/ananya")
	public String sawan() {
		return "i love u";
	}
}
