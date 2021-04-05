package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.services.CustomerUserDetails;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/test")
public class Home {
	@Autowired
	CustomerUserDetails customerUserDetails;
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	@PostMapping("/addblog")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String blogpart() {
		return "blog Board.";
	}

}
