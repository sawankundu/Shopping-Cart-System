package com.example.demo.controller;

import java.security.Principal;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtils;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.svc.impl.UserDetailsServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}catch (UsernameNotFoundException e) {
			throw new Exception("USER NOT FOUND");
		}
		
		UserDetails userDetails=  userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtUtils.generateToken(userDetails);
		return new ResponseEntity<>(new JwtResponse(token,userDetails), HttpStatus.OK);
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return ((User)userDetailsServiceImpl.loadUserByUsername(principal.getName()));
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch (DisabledException e) {
			throw new Exception("USER DISABLE"+ e.getMessage());
		}catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials" + e.getMessage());
		}
	}
	
	
}
