package com.Blogapp.services;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.Blogapp.dto.request.Loginrequest;
import com.Blogapp.dto.request.SignupRequest;
import com.Blogapp.dto.response.JwtResponse;
import com.Blogapp.dto.response.MessageResponse;
import com.Blogapp.helper.Jwtutil;
import com.Blogapp.model.Erole;
import com.Blogapp.model.Role;
import com.Blogapp.model.User;
import com.Blogapp.repo.RoleRepository;
import com.Blogapp.repo.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Service
public class CustomerUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String email;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private Jwtutil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	public CustomerUserDetails() {}
	public CustomerUserDetails(String id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static CustomerUserDetails build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new CustomerUserDetails(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}
	//for registering users
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
	//for authenticating users
	public ResponseEntity<?> authenticateUser( @RequestBody Loginrequest jwtRequest) throws Exception{

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken( jwtRequest.getUsername(),  jwtRequest.getPassword()));
		//to store authentication object inside the security context
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CustomerUserDetails user = (CustomerUserDetails) o;
		return Objects.equals(id, user.id);
	}
}
