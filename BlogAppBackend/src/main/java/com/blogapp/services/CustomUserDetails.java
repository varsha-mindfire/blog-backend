package com.blogapp.services;


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

import com.blogapp.constants.Message;
import com.blogapp.dto.request.DtoLoginRequest;
import com.blogapp.dto.request.DtoSignupRequest;
import com.blogapp.dto.response.JwtResponse;
import com.blogapp.dto.response.MessageResponse;
import com.blogapp.exception.RoleNotFoundException;
import com.blogapp.helper.JwtUtil;
import com.blogapp.model.Erole;
import com.blogapp.model.Role;
import com.blogapp.model.User;
import com.blogapp.repo.RoleRepository;
import com.blogapp.repo.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Service
public class CustomUserDetails implements UserDetails{
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
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	public CustomUserDetails() {}
	public CustomUserDetails(String id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static CustomUserDetails build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new CustomUserDetails(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}
	
	//for registering users
	public ResponseEntity<?> registerUser(@RequestBody DtoSignupRequest signuprequest) {
		if (userRepository.existsByUsername(signuprequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(Message.USERNAME_EXISTS));
		}

		if (userRepository.existsByEmail(signuprequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(Message.EMAIL_EXISTS));
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
					.orElseThrow(() -> new RoleNotFoundException(Message.ROLE_NOT_FOUND));
			roles.add(userRole);
		} else 
			
		{
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(Erole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "user":
					Role userRole = roleRepository.findByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
					break;
				default:
					throw new RoleNotFoundException(Message.ROLE_NOT_FOUND);
				}
			});
		}
			
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse(Message.USER_REGISTERED));
	}
	
	//for authenticating users
	public ResponseEntity<?> authenticateUser( @RequestBody DtoLoginRequest jwtRequest) throws Exception{

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken( jwtRequest.getUsername(),  jwtRequest.getPassword()));
		
		//to store authentication object inside the security context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateJwtToken(authentication);
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();		
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
		CustomUserDetails user = (CustomUserDetails) o;
		return Objects.equals(id, user.id);
	}
}
