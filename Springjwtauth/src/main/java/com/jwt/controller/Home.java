package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.UserModel;
import com.jwt.services.CustomerUserDetails;
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
	
	@GetMapping("/user/")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@RequestMapping(value="createresource",method=RequestMethod.POST)
	public String createBlog(@RequestBody UserModel userModel) {
		return customerUserDetails.createResource(userModel);
		
	}

}
