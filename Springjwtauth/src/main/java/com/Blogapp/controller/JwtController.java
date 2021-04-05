package com.Blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blogapp.dto.request.DtoLoginRequest;
import com.Blogapp.dto.request.DtoSignupRequest;
import com.Blogapp.dto.response.MessageResponse;
import com.Blogapp.repo.RoleRepository;
import com.Blogapp.repo.UserRepository;
import com.Blogapp.services.CustomUserDetails;

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
			customeruserdetails.registerUser(dtoSignupRequest);
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	    }
}