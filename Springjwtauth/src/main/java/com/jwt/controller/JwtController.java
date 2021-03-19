package com.jwt.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.Jwtutil;
import com.jwt.model.Erole;
import com.jwt.model.Role;
import com.jwt.model.User;
import com.jwt.payload.request.Loginrequest;
import com.jwt.payload.request.SignupRequest;
import com.jwt.payload.response.JwtResponse;
import com.jwt.payload.response.MessageResponse;
import com.jwt.repo.RoleRepository;
import com.jwt.repo.UserRepository;
import com.jwt.services.CustomerUserDetails;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class JwtController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private Jwtutil jwtUtil;
	@PostMapping(value="/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody Loginrequest jwtRequest) throws Exception{

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken( jwtRequest.getUsername(),  jwtRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtil.generateJwtToken(authentication);
			
			CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt, 
													 userDetails.getId(), 
													 userDetails.getUsername(), 
													 userDetails.getEmail(), 
													 roles));
		}

		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@RequestBody SignupRequest signuprequest) {
			if (userRepository.existsByUsername(signuprequest.getUsername())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Username is already taken!"));
			}

			if (userRepository.existsByEmail(signuprequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
			}

			// Create new user's account
			User user = new User( 
								 signuprequest.getEmail(),
								 encoder.encode(signuprequest.getPassword()),
								 signuprequest.getUsername());

			Set<String> strRoles = signuprequest.getRole();
			Set<Role> roles = new HashSet<>();
			

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(Erole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(Erole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(Erole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(Erole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}
			user.setRoles(roles);
			userRepository.save(user);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}

}
