package com.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.request.DtoLoginRequest;
import com.blogapp.dto.request.DtoSignupRequest;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.repo.RoleRepository;
import com.blogapp.repo.UserRepository;
import com.blogapp.services.CustomUserDetails;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class JwtController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CustomUserDetails customeruserdetails;

	@PostMapping(value="/signin")
	public ResponseEntity<?> signin( @RequestBody DtoLoginRequest jwtRequest) throws Exception{
		return  customeruserdetails.authenticateUser(jwtRequest);
	}

		@PostMapping("/signup")
		 public ResponseEntity<?> signup(@RequestBody DtoSignupRequest dtoSignupRequest) {
			return customeruserdetails.registerUser(dtoSignupRequest);
	    }
}